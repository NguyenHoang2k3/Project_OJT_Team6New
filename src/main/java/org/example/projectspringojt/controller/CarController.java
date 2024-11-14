package org.example.projectspringojt.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Feedback;
import org.example.projectspringojt.repository.CarRepository;

import org.example.projectspringojt.repository.FeedbackRepository;
import org.example.projectspringojt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;
    private final FeedbackRepository feedbackRepository;

//    public String listCar(Model model){
//        model.addAttribute("cars",carRepository.findAll());
//        return "customer/home";
//    }//    @GetMapping("/customer")


    @GetMapping("/car/home")
    public String listCars(Model model) {
        model.addAttribute("cars", carRepository.findTop9ByOrderByCarIdDesc());
        return "/home";
    }

    @GetMapping("/carDetail/{carId}")
    public String getCar(@PathVariable Integer carId, Model model){
        Car car = carRepository.findById(carId).orElseThrow();
        BigDecimal totalPrice = car.getRentalPrice().add(car.getPrice());
        CreateCar createCar = new CreateCar();
        BeanUtils.copyProperties(car, createCar);

        List<Car> carSameBrand = carRepository.findByBrand(car.getBrand());
//        List<Feedback> feedbackOfCar = feedbackRepository.findByCar(car);
//        feedbackOfCar.sort((f1, f2) -> f2.getTime().compareTo(f1.getTime()));
//        model.addAttribute("feedbacks", feedbackOfCar);
        model.addAttribute("car", createCar);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("carSameBrand", carSameBrand);

        return "/carDetail";
    }



}
