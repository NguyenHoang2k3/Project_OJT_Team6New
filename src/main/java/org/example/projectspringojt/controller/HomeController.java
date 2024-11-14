package org.example.projectspringojt.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
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
