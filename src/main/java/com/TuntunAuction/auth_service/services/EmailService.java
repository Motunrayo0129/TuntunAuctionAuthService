package com.TuntunAuction.auth_service.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendVerificationEmail(String to, String fullname, String verificationCode);
}
