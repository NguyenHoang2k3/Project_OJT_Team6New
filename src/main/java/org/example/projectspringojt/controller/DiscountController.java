package org.example.projectspringojt.controller;

import org.example.projectspringojt.entity.TicketsDiscount;
import org.example.projectspringojt.repository.TicketsDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private TicketsDiscountRepository ticketsDiscountRepository;

    private final double originalPrice = 943228; // Tổng tiền gốc

    @GetMapping("/discounts")
    public String showDiscounts(Model model) {
        List<TicketsDiscount> discounts = ticketsDiscountRepository.findAll(); // Lấy tất cả các mã giảm giá
        model.addAttribute("discounts", discounts);
        return "/user/discounts"; // Trả về trang HTML
    }

    @PostMapping("/discounts")
    public String applyDiscount(@RequestParam("code") String code, Model model) {

        TicketsDiscount discount = ticketsDiscountRepository.findAll()
                .stream()
                .filter(d -> d.getTicketsName().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (discount != null) {

            double discountAmount = originalPrice * (discount.getDiscount() / 100.0);
            double finalPrice = originalPrice - discountAmount;


            model.addAttribute("selectedDiscount", discount);
            model.addAttribute("finalPrice", finalPrice);
        } else {
            model.addAttribute("error", "Mã giảm giá không hợp lệ!");
        }


        List<TicketsDiscount> discounts = ticketsDiscountRepository.findAll();
        model.addAttribute("discounts", discounts);
        return "/user/discounts";
    }
}
