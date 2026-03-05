package com.TuntunAuction.auth_service.services;

import com.TuntunAuction.auth_service.data.models.Role;
import com.TuntunAuction.auth_service.data.models.User;
import com.TuntunAuction.auth_service.data.repositories.UserRepository;
import com.TuntunAuction.auth_service.dtos.requests.RegisterRequest;
import com.TuntunAuction.auth_service.dtos.responses.RegisterResponse;
import com.TuntunAuction.auth_service.exceptions.PasswordMismatchException;
import com.TuntunAuction.auth_service.exceptions.UnauthorizedException;
import com.TuntunAuction.auth_service.exceptions.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j

public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducerService kafkaProducerService;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Password do not match");
        }

        if (request.getRole() == Role.ADMIN) {
            throw new UnauthorizedException("Cannot register as ADMIN");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("Email already exist");
        }

        if (userRepository.existsByPhoneNo(request.getPhoneNo())) {
            throw new UserAlreadyExistException("Phone number already exist");
        }

        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.BIDDER);
        user.setVerified(false);

        String verificationCode = String.valueOf(new Random().nextInt(900000) + 100000);
        user.setVerificationCode(verificationCode);
        user.setVerificationCodeExpiry(LocalDateTime.now().plusMinutes(15));

        User savedUser = userRepository.save(user);

        try {
            kafkaProducerService.sendUserRegisteredEvent(savedUser, verificationCode);
        } catch (Exception e) {
            log.error("Failed to send Kafka event: {}", e.getMessage());
        }

        log.info("User registered successfully: {}", savedUser.getEmail());
        return modelMapper.map(savedUser, RegisterResponse.class);
    }




}
