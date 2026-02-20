package com.TuntunAuction.auth_service.dtos.responses;

import com.TuntunAuction.auth_service.data.models.Role;

import java.time.LocalDateTime;

public class RegisterResponse {

    private String refreshToken;
    private String id;
    private String fullname;
    private String email;
    private String phoneNo;
    private Role role;
    private LocalDateTime createdAt;
    private String verificationCode;
}
