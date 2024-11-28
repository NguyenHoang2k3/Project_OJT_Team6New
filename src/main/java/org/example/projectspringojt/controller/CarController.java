package org.example.projectspringojt.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Feedback;
import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.repository.CarRepository;

import org.example.projectspringojt.repository.FeedbackRepository;
import org.example.projectspringojt.service.CarService;
import org.example.projectspringojt.service.UserService;
import org.example.projectspringojt.service.impl.CarServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;
    private final CarService carService;
    private final FeedbackRepository feedbackRepository;

    @GetMapping("/car/home")
    public String listCars(Model model) {
        model.addAttribute("cars", carRepository.findTop9ByAcpCarStatusTrueOrderByCarIdDesc());
        return "home";
    }

    @GetMapping("/carDetail/{carId}")
    public String getCar(@PathVariable Integer carId, Model model){
        Car car = carRepository.findById(carId).orElseThrow();
        BigDecimal totalPrice = car.getRentalPrice().add(car.getPrice());
        List<Car> carSameBrand = carRepository.findByBrand(car.getBrand());
        List<Feedback> feedbacks = feedbackRepository.findByOrder_Cars_CarId(carId);

        double averageRating = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        int totalComments = feedbacks.size();
        Order order = feedbacks.isEmpty() ? null : feedbacks.get(0).getOrder();


        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("averageRating", String.format("%.1f", averageRating));
        model.addAttribute("totalComments", totalComments);

        model.addAttribute("car", car);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("carSameBrand", carSameBrand);

        return "/carDetail";
    }

    @GetMapping("/myListCars")
    public String showMyCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "myCars";
    }

}
