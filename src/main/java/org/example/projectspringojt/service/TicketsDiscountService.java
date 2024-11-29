package org.example.projectspringojt.service;

import org.example.projectspringojt.dto.TicketsDiscountDTO;
import org.example.projectspringojt.entity.TicketsDiscount;

import java.util.List;

public interface TicketsDiscountService {
    TicketsDiscount addTicketsDiscount(TicketsDiscountDTO ticketsDiscountDTO);
    List<TicketsDiscount> getAllTicketsDiscounts();
    TicketsDiscount getTicketById(Integer id);
    void updateTicketsDiscount(Integer id, TicketsDiscountDTO ticketsDiscountDTO);
    void deleteTicketsDiscount(Integer id);
}
