package org.example.projectspringojt.repository;

import java.util.List;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Integer> {

  @Query("SELECT u FROM Car u " +
      "WHERE u.name LIKE %:searchText% " +
      "OR u.brand LIKE %:searchText% " +
      "OR u.model LIKE %:searchText% " +
      "OR u.color LIKE %:searchText% " +
      "OR CAST(u.carStatus AS string) LIKE %:searchText%")
  List<Car> findByAllFields(String searchText);
}
