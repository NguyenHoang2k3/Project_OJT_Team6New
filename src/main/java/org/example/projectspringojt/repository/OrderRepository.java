package org.example.projectspringojt.repository;


import org.example.projectspringojt.entity.Order;
import org.example.projectspringojt.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByCars_CarId(Integer carId);

    @Query("SELECT u FROM Order u " +
            "WHERE STR(u.OrderStartDate) LIKE %:searchText% " +
            "OR STR(u.OrderEndDate) LIKE %:searchText% " +
            "OR STR(u.cars.carId) LIKE %:searchText% " +
            "OR STR(u.user.userID) LIKE %:searchText% " +
            "OR CAST(u.status AS String) LIKE %:searchText%")
    List<Order> findByAllFields(String searchText);

    @Query("select o from Order o " +
            "where o.cars.name like :searchText " +
            "or o.sh_address like :searchText " +
            "or CONCAT(o.OrderStartDate, '') like :searchText " +
            "or CONCAT(o.OrderEndDate, '') like :searchText")
    Page<Order> findByAllField(String searchText, Pageable pageable);

    @Query("select o from Order o where o.status = :status order by o.orderId desc")
    Page<Order> findByStatusOrderedByIdDesc(@Param("status") Status status, Pageable pageable);

    long countByStatus(Status status);

    @Query("SELECT SUM(o.orderPrice) FROM Order o WHERE o.status = 'DONE' GROUP BY MONTH(o.OrderStartDate) order by MONTH(o.OrderStartDate) asc")
    List<Long> getMonthlyRevenueByYear();

    @Query("SELECT DISTINCT YEAR(o.OrderStartDate) FROM Order o WHERE o.status = 'DONE' GROUP BY YEAR(o.OrderStartDate) order by YEAR(o.OrderStartDate) ASC")
    List<String> getYearRevenue();


}
