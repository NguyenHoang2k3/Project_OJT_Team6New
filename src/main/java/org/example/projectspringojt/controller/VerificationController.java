package org.example.projectspringojt.controller;

import org.example.projectspringojt.service.UserService;
import org.example.projectspringojt.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerificationController {

    @Autowired
    private UserServiceTest userService;

    @GetMapping("/verify-email")
    public String showEmailForm() {
        return "sendEmail";
    }

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestParam String email, Model model) {
        try {
            userService.sendVerificationCode(email);
            model.addAttribute("message", "Mã xác minh được gửi đến email của bạn.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());


            return "sendEmail";
        }

        return "redirect:/reset-password";
    }
    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "resetPass";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String otp,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
            return "resetPass";
        }

        try {
            userService.resetPassword(otp, newPassword);
            model.addAttribute("message", "Mật khẩu đã được cập nhật thành công.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "resetPass";
    }
}



