package org.example.projectspringojt.repository;


import org.example.projectspringojt.entity.TicketsDiscount;




import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsDiscountRepository extends JpaRepository<TicketsDiscount, Integer> {
    List<TicketsDiscount> findByTicketsId(Integer ticketsId);
}
