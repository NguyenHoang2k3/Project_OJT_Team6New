package org.example.projectspringojt.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.CarStatus;
import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.repository.OrderRepository;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.extras.springsecurity6.util.SpringSecurityContextUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarService carService;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/addCar")
    public String addCarForm(Model model) {
        CreateCar createCar = new CreateCar();
        model.addAttribute("car", createCar);
        return "carOwner/addCar";
    }
    @PostMapping("/addCar")
    public String saveCar(@Validated @ModelAttribute("car") CreateCar createCar, BindingResult result, RedirectAttributes redirectAttributes) throws IOException{
        if (result.hasErrors()) {
            return "carOwner/addCar";
        }
        carService.save(createCar);
        redirectAttributes.addFlashAttribute("messages", "Car added successfully");
        return "redirect:/myCars#xe-cua-toi";
    }

}
