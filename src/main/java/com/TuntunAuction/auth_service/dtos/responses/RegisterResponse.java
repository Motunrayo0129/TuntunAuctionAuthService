package com.TuntunAuction.auth_service.dtos.responses;

import com.TuntunAuction.auth_service.data.models.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponse {

    private Long id;
    private String fullname;
    private String email;
    private String phoneNo;
    private Role role;
    private LocalDateTime createdAt;

}
