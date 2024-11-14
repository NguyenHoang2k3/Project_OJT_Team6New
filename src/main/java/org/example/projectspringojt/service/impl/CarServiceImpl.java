package org.example.projectspringojt.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public void save(CreateCar createCar) throws IOException {
            Car car = new Car();
            BeanUtils.copyProperties(createCar, car);
            carRepository.save(car);
        }
    }

