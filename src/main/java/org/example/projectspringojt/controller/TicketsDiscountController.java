package org.example.projectspringojt.controller;

import jakarta.validation.Valid;
import org.example.projectspringojt.dto.TicketsDiscountDTO;
import org.example.projectspringojt.entity.TicketsDiscount;
import org.example.projectspringojt.service.TicketsDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TicketsDiscountController {

    @Autowired
    private TicketsDiscountService ticketsDiscountService;

    @GetMapping("/tickets/add")
    public String showAddForm(Model model) {
        model.addAttribute("ticketsDiscountDTO", new TicketsDiscountDTO());
        return "/user/addtickets";
    }

    @PostMapping("/tickets/add")
    public String addTicketsDiscount(@ModelAttribute("ticketsDiscountDTO") @Valid TicketsDiscountDTO ticketsDiscountDTO,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Error");
            return "/user/addtickets";
        }

        try {
            ticketsDiscountService.addTicketsDiscount(ticketsDiscountDTO);
            model.addAttribute("message", "Tickets discount added successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/user/addtickets";
        }

        return "redirect:/tickets/list";
    }
    @GetMapping("/tickets/list")
    public String listTicketsDiscount(Model model) {
        List<TicketsDiscount> ticketsDiscounts = ticketsDiscountService.getAllTicketsDiscounts();
        model.addAttribute("ticketsDiscounts", ticketsDiscounts);
        return "/user/listtickets";
    }



    // Method to handle ticket discount deletion
    @GetMapping("/tickets/delete/{id}")
    public String deleteTicketsDiscount(@PathVariable("id") Integer id, Model model) {
        try {
            ticketsDiscountService.deleteTicketsDiscount(id);
            model.addAttribute("message", "Tickets discount deleted successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/tickets/list";
    }






    @GetMapping("/tickets/edit/{ticketsId}")
    public String showEditForm(@PathVariable("ticketsId") Integer ticketsId, Model model) {
        TicketsDiscount ticket = ticketsDiscountService.getTicketById(ticketsId);

        TicketsDiscountDTO ticketDTO = new TicketsDiscountDTO();
        ticketDTO.setTicketsId(ticket.getTicketsId());
        ticketDTO.setTicketsName(ticket.getTicketsName());
        ticketDTO.setDiscount(ticket.getDiscount());
        ticketDTO.setUserId(ticket.getUser().getUserID().longValue());
        model.addAttribute("ticketsDiscountDTO", ticketDTO);
        return "/user/editTicketsDiscount";
    }

    @PostMapping("/tickets/edit/{ticketsId}")
    public String updateTicketsDiscount(@PathVariable("ticketsId") Integer ticketsId,
                                        @ModelAttribute("ticketsDiscountDTO") @Valid TicketsDiscountDTO ticketsDiscountDTO,
                                        BindingResult bindingResult,
                                        Model model) {


        try {
            ticketsDiscountService.updateTicketsDiscount(ticketsId, ticketsDiscountDTO);
            model.addAttribute("message", "Cập nhật vé thành công.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/user/editTicketsDiscount";
        }

        return "redirect:/tickets/list";
    }







}