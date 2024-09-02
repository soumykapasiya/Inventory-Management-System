package com.kapasiya.ims.inventorymanagementsystem.service.impl.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.LoginRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.request.UserRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.LoginResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.auth.Role;
import com.kapasiya.ims.inventorymanagementsystem.entities.auth.User;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.RoleException;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.UserException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.RoleMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.mongo.RoleRepository;
import com.kapasiya.ims.inventorymanagementsystem.repository.mongo.UserRepository;
import com.kapasiya.ims.inventorymanagementsystem.service.def.auth.AuthService;
import com.kapasiya.ims.inventorymanagementsystem.utils.JwtUtil;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExecutorService virtualThreadExecutor;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.token-validity}")
    private Long jwtExpirationInMS;

    @Override
    public CustomResponseDto<Void> createRole(RoleRequestDto requestDTO) {
        log.info("Creating Role with Request: {} ", requestDTO);
        try {
            if (roleRepository.existsByName(requestDTO.getName())) {
                log.info("Role with name {} already exists", requestDTO.getName());
                throw new RoleException("Role with name " + requestDTO.getName() + " already exists");
            }
            Role role = RoleMapper.toEntity(requestDTO);
            roleRepository.save(role);
            log.info("Role with name {} has been created", requestDTO.getName());

            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Role Created Successfully..");
        } catch (RoleException re) {
            log.error("Exception Wile Creating Role: {}", re.getMessage());
            throw re;
        } catch (Exception e) {
            log.error("Exception Occurred Wile Creating Role: {}", e.getMessage(), e);
            throw new RoleException("Error while creating Role: " + requestDTO.getName());
        }
    }

    @Override
    @Transactional
    public CustomResponseDto<Void> register(UserRequestDto requestDto) {

        log.info("Entering register method in AuthServiceImpl with request: {}",requestDto);
        try {
            if (userRepository.existsByEmail(requestDto.getEmail())) {
                log.info("Email already exists: {}", requestDto.getEmail());
                throw new UserException("User is already exist with this email: " + requestDto.getEmail());
            }
            Role role = roleRepository.findById(requestDto.getRoleId()).orElseThrow(() -> {
                log.error("Role not found with id{} while creating user with email{}", requestDto.getRoleId(), requestDto.getEmail());
                return new UserException("Role not found while creating user: " + requestDto.getRoleId());
            });

            User user = User.builder()
                    .userName(requestDto.getName())
                    .email(requestDto.getEmail())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .phone(requestDto.getPhone())
                    .roles(Collections.singleton(role))
                    .build();

            userRepository.save(user);
            return ResponseUtil.successMessageResponse(HttpStatus.CREATED,"User registered successfully");
        }catch (UserException ue){
            log.error("Exception Wile Creating User: {}", ue.getMessage());
            throw ue;
        } catch (Exception e) {
            log.error("Exception Occurred Wile Creating User:{}", e.getMessage(), e);
            throw new UserException("Error while creating user: " + requestDto.getEmail());
        }
    }

    @Override
    public CustomResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto) {

        // Authenticate user in virtual thread
        CompletableFuture<Void> authenticationFuture = CompletableFuture.runAsync(() -> {
                    try {
                        log.info("Authentication user with email: {}", loginRequestDto.getEmail());
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
                    } catch (Exception e) {
                        throw new UserException("Authentication failed: {}" + e.getMessage());
                    }
                },
                virtualThreadExecutor);

        // Fetch user details in a virtual thread
        CompletableFuture<UserDetails> userDetailsFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        log.info("Loading user details for email: {}", loginRequestDto.getEmail());
                        return userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
                    } catch (Exception e) {
                        log.info("Error loading user details for email: {}", loginRequestDto.getEmail());
                        throw new UserException("Loading user details failed" + e.getMessage());
                    }
                },
                virtualThreadExecutor);

        // Fetch customer in a Virtual Thread
        CompletableFuture<User> customerFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        log.info("Finding customer by email: {}", loginRequestDto.getEmail());
                        return userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new UserException("User not found with email: " + loginRequestDto.getEmail()));
                    } catch (Exception e) {
                        log.info("Error finding customer by email: {}", loginRequestDto.getEmail());
                        throw new UserException("Finding customer failed: " + e.getMessage());
                    }
                },
                virtualThreadExecutor);

        // Combine future and wait for all to complete
        CompletableFuture<Void> combineFuture = CompletableFuture.allOf(authenticationFuture, userDetailsFuture, customerFuture);

        // Process results once all future are done
        return combineFuture.thenApplyAsync(v -> {
            authenticationFuture.join();
            UserDetails userDetails = userDetailsFuture.join();
            User userEntity = customerFuture.join();

            // Generate Jwt token
            String token = generateToken(userDetails);

            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getUserName())
                    .email(userEntity.getEmail())
                    .accessToken(token)
                    .refreshToken("")
                    .tokenExpiryTime(formatter())
                    .build();
            return ResponseUtil.successDataResponse(HttpStatus.OK, "User Successfully logged in", loginResponseDto);
        }).exceptionally(ex -> {
            throw new UserException(ex.getMessage());
        }).join();
    }
    private String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails);
    }

    private String formatter() {
        Instant tokenExpiryInstant = Instant.now().plusMillis(jwtExpirationInMS);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()).format(tokenExpiryInstant);
    }
}
