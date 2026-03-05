package com.TuntunAuction.auth_service.services;

import com.TuntunAuction.auth_service.data.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendUserRegisteredEvent(User user, String verificationCode) {
        Map<String, Object> event = new HashMap<>();
        event.put("userId", user.getId());
        event.put("fullname", user.getFullname());
        event.put("email", user.getEmail());
        event.put("verificationCode", user.getVerificationCode());

        kafkaTemplate.send("user-registered", event);
        log.info("User registered event sent for: {}", user.getEmail());
    }

}
