package com.TuntunAuction.auth_service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService{

    private  final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String to, String fullname, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Tuntun Naija Auction - Verify Your Email");
            message.setText(
                    "Hello " + fullname + ", \n\n"
                    + "Thank you for registering at Tuntun Auction!\n"
                    + "Please use the following verification code to verify your email address:\n\n"
                    + verificationCode + "\n\n"
                    + "If you did not register, please ignore this email.\n\n"
                    + "Best regards,\nTuntun Auction Team"
            );
            message.setFrom("tutunnaijaauctions@gmail.com");

            mailSender.send(message);

            log.info("Verification email sent to {}", to);
        }catch (Exception ex) {
            log.error("Failed to send email to {}: {}", to, ex.getMessage());

        }


    }


}
