package com.TuntunAuction.auth_service.data.repositories;

import com.TuntunAuction.auth_service.data.models.Role;
import com.TuntunAuction.auth_service.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNo(String phoneNo);
    Optional<User> findByVerificationCode(String verificationCode);
    Optional<User> findByResetPasswordToken(String resetPasswordToken);

    boolean existsByEmail(String email);
    boolean existsByPhoneNo(String phoneNo);

    List<User> findAllByRole(Role role);

    List<User> findByIsVerified(boolean isVerified);

    void deleteByEmail(String email);





}
