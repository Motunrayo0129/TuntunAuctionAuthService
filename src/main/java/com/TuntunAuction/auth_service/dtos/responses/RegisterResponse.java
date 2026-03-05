package com.TuntunAuction.auth_service.dtos.responses;

import com.TuntunAuction.auth_service.data.models.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private String message;
    private UserResponse response;
    private LocalDateTime createdAt;

}
