package org.example.projectspringojt.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.projectspringojt.dto.request.CreateCar;
import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Role;
import org.example.projectspringojt.entity.User;
import org.example.projectspringojt.repository.CarRepository;
import org.example.projectspringojt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final UserRepository userRepository;

  @Override
  public void save(CreateCar createCar) throws IOException {
    // Implement logic to save the car here
  }

  public void rejectCar(Integer carId, String reason, LocalDate timeBan) {
    Optional<Car> carOptional = carRepository.findById(carId);

    if (carOptional.isPresent()) {
      Car car = carOptional.get();

      car.setAcpCarStatus(true);
      car.setReason(reason);
      car.setTimeBan(timeBan);
      carRepository.save(car);

      User user = car.getUser();

      if (user != null) {
        user.setRole(Role.CAR_OWNER);
        userRepository.save(user);
      }
    }
  }

  public void changeCarStatus(Integer carId, Boolean acpCarStatus) {
    Optional<Car> carOptional = carRepository.findById(carId);
    if (carOptional.isPresent()) {
      Car car = carOptional.get();
      car.setAcpCarStatus(acpCarStatus);
      carRepository.save(car);
    }
  }
}
