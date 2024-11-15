package org.example.projectspringojt.service.impl;

import org.example.projectspringojt.dto.TicketsDiscountDTO;
import org.example.projectspringojt.entity.TicketsDiscount;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.TicketsDiscountRepository;
import org.example.projectspringojt.repository.UserRepository;
import org.example.projectspringojt.service.TicketsDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsDiscountServiceImpl implements TicketsDiscountService {

    @Autowired
    private TicketsDiscountRepository ticketsDiscountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TicketsDiscount addTicketsDiscount(TicketsDiscountDTO ticketsDiscountDTO) {
        User user = userRepository.findById(ticketsDiscountDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        TicketsDiscount ticketsDiscount = new TicketsDiscount();
        ticketsDiscount.setTicketsName(ticketsDiscountDTO.getTicketsName());
        ticketsDiscount.setDiscount(ticketsDiscountDTO.getDiscount());
        ticketsDiscount.setUser(user);

        return ticketsDiscountRepository.save(ticketsDiscount);
    }
    @Override
    public List<TicketsDiscount> getAllTicketsDiscounts() {
        return ticketsDiscountRepository.findAll();
    }
    public void updateTicketsDiscount(Integer id, TicketsDiscountDTO ticketsDiscountDTO) {
        User user = userRepository.findById(ticketsDiscountDTO.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User not found with ID: " + ticketsDiscountDTO.getUserId())
        );


        TicketsDiscount existingTicket = ticketsDiscountRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Ticket not found with ID: " + id)
        );


        existingTicket.setTicketsName(ticketsDiscountDTO.getTicketsName());
        existingTicket.setDiscount(ticketsDiscountDTO.getDiscount());
        existingTicket.setUser(user);


        ticketsDiscountRepository.save(existingTicket);
    }

    // Delete ticket discount by ID
    public void deleteTicketsDiscount(Integer id) {
        TicketsDiscount ticket = ticketsDiscountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        ticketsDiscountRepository.delete(ticket);
    }

    // Fetch ticket by ID (for editing)
    public TicketsDiscount getTicketById(Integer id) {
        return ticketsDiscountRepository.findById(id).orElse(null);
    }
}
