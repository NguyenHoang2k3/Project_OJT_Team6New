package org.example.projectspringojt.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateOrder;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.entity.Status;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.repository.OrderRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final CarRepository carRepository;

@GetMapping("/myCars")
public String getOrderList(
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "3") Integer size,
        Model model) {

    Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("orderId")));

    Page<Order> pageOrders;

    if (search != null && !search.isEmpty()) {
        pageOrders = orderRepository.findByAllField("%" + search + "%", pageable);
    }
    else if (status != null && !status.isEmpty() && !status.equals("ALL")) {
        try {
            Status statusEnum = Status.valueOf(status.toUpperCase());
            pageOrders = orderRepository.findByStatusOrderedByIdDesc(statusEnum, pageable);
            if (pageOrders.isEmpty()) {
                    model.addAttribute("notification", "Order list not found.");
                }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid status selected");
            pageOrders = orderRepository.findAll(pageable);
        }
    }
    else {
        pageOrders = orderRepository.findAll(pageable);
    }

    List<Order> orders = pageOrders.getContent();

    model.addAttribute("orders", orders);
    model.addAttribute("currentPage", page);
    model.addAttribute("size", size);
    model.addAttribute("totalPages", pageOrders.getTotalPages());
    model.addAttribute("totalItems", pageOrders.getTotalElements());

    model.addAttribute("status", status);
    model.addAttribute("search", search);

    return "myCars";
}



    @GetMapping("/ChangeStatusOrder")
    public String changeOrderStatus(
            @RequestParam Integer orderId,
            Model model) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new EntityNotFoundException("Not found entity with id: " + orderId);
        });
        if(order.getStatus().equals(Status.PROGRESS)){
            order.setStatus(Status.CANCELLED);
        } else if (order.getStatus().equals(Status.CANCELLED)) {
            return "redirect:/carDetail/" + order.getCars().getCarId();
        }else{
            return "redirect:/carDetail/" + order.getCars().getCarId();
        }
        orderRepository.save(order);
        return "redirect:/myCars#chuyen-cua-toi";
    }


}
