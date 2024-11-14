package org.example.projectspringojt.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.projectspringojt.entity.CarStatus;


import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class CreateCar {
    private Integer carId;

    @NotBlank(message = "CarName is required!")
    private String name;

    private String licensePlate;

    @NotBlank(message = "Brand is required!")
    private String brand;

    @NotBlank(message = "Model is required!")
    private String model;

    private String color;

    @NotNull(message = "Number Of Seats cannot be null")
    private Integer numberOfSeats;

    private LocalDate productionYears;

    @NotBlank(message = "Fuel Type is required!")
    private String fuelType;

    @NotBlank(message = "Fuel Consumption is required!")
    private String fuelConsumption;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Rental price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rental price must be greater than zero")
    private BigDecimal rentalPrice;

    @NotBlank(message = "address is required!")
    private String address;

    @NotBlank(message = "please select photo !")
    private String image;

    private String termOfUse;
    private CarStatus carStatus;
    private Boolean addressCarStatus;

    @NotBlank(message = "please select photo !")
    private String vehicleRegistration;
}
