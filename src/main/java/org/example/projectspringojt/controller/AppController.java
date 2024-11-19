package org.example.projectspringojt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AppController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Trang home chính cho tất cả người dùng
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "adminHome"; // Đường dẫn đúng đến template adminHome.html
    }

    @GetMapping("/carOwner/home")
    public String carOwnerHome() {
        return "carOwnerHome"; // Đường dẫn đúng đến template carOwnerHome.html
    }

    @GetMapping("/customer/home")
    public String customerHome() {
        return "customerHome"; // Có thể bạn muốn tạo một template riêng cho customer, ví dụ "customerHome"
    }

    @GetMapping("/carDetail")
    public String getCarDetails() {
        return "carDetail";
    }

    @GetMapping("/myCars")
    public String getMyCars() {
        return "myCars";
    }
}
