package org.example.projectspringojt.repository;

import java.util.List;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Feedback;
import org.example.projectspringojt.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer>{
  @Query("SELECT u FROM Order u " +
      "WHERE STR(u.OrderStartDate) LIKE %:searchText% " +
      "OR STR(u.OrderEndDate) LIKE %:searchText% " +
      "OR STR(u.cars.carId) LIKE %:searchText% " +
      "OR STR(u.user.userID) LIKE %:searchText% " +
      "OR CAST(u.Status AS String) LIKE %:searchText%")
  List<Order> findByAllFields(String searchText);


}
