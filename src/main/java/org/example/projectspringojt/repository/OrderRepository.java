package org.example.projectspringojt.repository;

import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o " +
            "where o.cars.name like :searchText " +
            "or o.sh_address like :searchText " +
            "or CONCAT(o.OrderStartDate, '') like :searchText " +
            "or CONCAT(o.OrderEndDate, '') like :searchText")
    Page<Order> findByAllField(String searchText, Pageable pageable);

    @Query("select o from Order o where o.status = :status order by o.orderId desc")
    Page<Order> findByStatusOrderedByIdDesc(@Param("status") Status status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.user.userID = :userId AND o.status = 'PROGRESS'")
    List<Order> findOrdersByUser(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.cars.user.userID = :ownerId AND o.status = 'PROGRESS'")
    List<Order> findOrdersByCarOwner(@Param("ownerId") Integer ownerId);

}
