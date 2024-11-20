package org.example.projectspringojt.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.entity.Feedback;
import org.example.projectspringojt.repository.FeedbackRepository;
import org.example.projectspringojt.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/feedbacks/{carId}")
    public String showFeedbacks(@PathVariable Integer carId, Model model) {
        List<Feedback> feedbacks = feedbackRepository.findByOrder_Cars_CarId(carId); // Lấy theo carId

        double averageRating = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        int totalComments = feedbacks.size();

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("averageRating", String.format("%.1f", averageRating));
        model.addAttribute("totalComments", totalComments);

        return "/user/feedback";
    }

    @GetMapping("/feedbacks/add/order/{orderId}")
    public String showAddFeedbackForm(

            @PathVariable Integer orderId
            , Model model) {
        model.addAttribute("orderId", orderId);

        return "/user/addFeedback";
    }

    @PostMapping("/feedbacks/add/order/{orderId}")
    public String addFeedback(@PathVariable Integer orderId,
                              @RequestParam("rating") Integer rating,
                              @RequestParam("content") String content
                             ) {

        Feedback feedback = new Feedback();
        feedback.setRating(rating);
        feedback.setContent(content);
        feedback.setTime(LocalDate.now());

        // Liên kết feedback với order
        feedback.setOrder(orderRepository.findById(orderId).orElse(null));

        feedbackRepository.save(feedback);

        Integer carId = feedback.getOrder().getCars().getCarId();


        return "redirect:/feedbacks/" + carId;
    }
}