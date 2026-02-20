package com.TuntunAuction.auth_service.dtos.requests;

import com.TuntunAuction.auth_service.data.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

    @NotBlank(message = "Fullname cannot be blank")
    private String fullname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&$#])[A-Za-z\\d@$!%*?&$#]{12,}$",
            message = "Password must be at least 12 characters with uppercase, lowercase, number and special character"
    )
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^(\\+234|0)[0-9]{10}$",
            message = "Invalid phone number format (e.g 08012345678 or +2348012345678) "
    )
    private String phoneNo;

    private Role role;
}
