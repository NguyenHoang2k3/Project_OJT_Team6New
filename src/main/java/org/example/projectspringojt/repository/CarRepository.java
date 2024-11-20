package org.example.projectspringojt.repository;

import org.example.projectspringojt.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {


  @Query("SELECT u FROM Car u " +
      "WHERE u.name LIKE %:searchText% " +
      "OR u.brand LIKE %:searchText% " +
      "OR u.model LIKE %:searchText% " +
      "OR u.color LIKE %:searchText% " +
      "OR CAST(u.carStatus AS string) LIKE %:searchText%")
  List<Car> findByAllFields(String searchText);

    @Query("SELECT c FROM Car c WHERE c.AcpCarStatus = true ORDER BY c.carId DESC")
    List<Car> findTop9ByAcpCarStatusTrueOrderByCarIdDesc();
    List<Car> findByBrand(String brand);

}
