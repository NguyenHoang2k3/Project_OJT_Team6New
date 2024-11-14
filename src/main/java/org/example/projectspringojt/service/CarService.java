package org.example.projectspringojt.service;

import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.dto.request.CreateUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarService {
    void save(CreateCar createCar) throws IOException;
}
