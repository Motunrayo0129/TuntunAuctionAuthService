package com.TuntunAuction.auth_service.controllers;

import com.TuntunAuction.auth_service.dtos.requests.LoginRequest;
import com.TuntunAuction.auth_service.dtos.responses.LoginResponse;
import com.TuntunAuction.auth_service.services.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor

public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = loginService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
