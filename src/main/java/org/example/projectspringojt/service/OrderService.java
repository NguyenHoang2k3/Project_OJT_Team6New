package org.example.projectspringojt.service;

import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.dto.request.CreateOrder;

import java.io.IOException;

public interface OrderService {
    void save(CreateOrder createOrder) throws IOException;
}
