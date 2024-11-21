package org.example.projectspringojt.controller;

import org.example.projectspringojt.dto.RegisterUserDTO;
import org.example.projectspringojt.service.UserService;
import org.example.projectspringojt.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.io.IOException;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceTest userService;



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.addAttribute("user", registerUserDTO);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegisterUserDTO registerUserDTO,
                               BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "register";
        }

        try {
            userService.registerUser(registerUserDTO);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "error.user", e.getMessage());
            return "register";
        }
        return "redirect:/Home";
    }
}
