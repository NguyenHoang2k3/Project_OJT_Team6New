package org.example.projectspringojt.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarService carService;
    private final CarRepository carRepository;

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
