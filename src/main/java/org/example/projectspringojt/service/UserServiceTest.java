package org.example.projectspringojt.service;

import org.example.projectspringojt.dto.RegisterUserDTO;

import java.io.IOException;

public interface UserServiceTest {
    void registerUser(RegisterUserDTO registerUserDTO) throws IOException;
    void sendVerificationCode(String email) throws IllegalArgumentException;
    void resetPassword(String otp, String newPassword) throws IllegalArgumentException;
}
