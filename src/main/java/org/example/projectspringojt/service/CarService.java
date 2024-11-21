package org.example.projectspringojt.service;

import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.dto.request.CreateUser;
import org.example.projectspringojt.entity.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
  void save(CreateCar createCar) throws IOException;
  List<Car> getAllCars();
}