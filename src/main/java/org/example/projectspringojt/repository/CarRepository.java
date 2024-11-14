package org.example.projectspringojt.repository;

import org.example.projectspringojt.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findTop9ByOrderByCarIdDesc();
    List<Car> findByBrand(String brand);
}
