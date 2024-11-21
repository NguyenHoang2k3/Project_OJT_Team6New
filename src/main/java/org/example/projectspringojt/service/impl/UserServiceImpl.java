package org.example.projectspringojt.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.RegisterUserDTO;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.EmailService;
import org.example.projectspringojt.service.UserService;
import org.example.projectspringojt.service.UserServiceTest;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceTest {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private Map<String, String> otpStore = new HashMap<>();

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) throws IOException {



        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }


        User user = new User();
        BeanUtils.copyProperties(registerUserDTO, user);

        // ma hoa pass
//        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        user.setPassword(registerUserDTO.getPassword());

        userRepository.save(user);

    }

    @Override
    public void sendVerificationCode(String email) throws IllegalArgumentException {
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email does not exist in the database");
        }


        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        otpStore.put(email, verificationCode);
        String subject = "Verification Code";
        String text = "Your verification code is: " + verificationCode;
        emailService.sendEmail(email,subject,text);

        System.out.println("Sending verification code to " + email + ": " + verificationCode);


    }

    @Override
    public void resetPassword(String otp, String newPassword) throws IllegalArgumentException {

        String email = otpStore.entrySet().stream()
                .filter(entry -> otp.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Mã OTP không hợp lệ"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));


        //user.setPassword(passwordEncoder.encode(newPassword));
        user.setPassword(newPassword);
        userRepository.save(user);


        otpStore.remove(email);
    }


}

