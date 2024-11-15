package org.example.projectspringojt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Car")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Car_ID")
  private Integer carId;

  @Column(name = "Name", nullable = false, length = 50)
  private String name;


  @Column(name = "LicensePlate", length = 50, nullable = false)
  private String licensePlate;

  @Column(name = "Brand", length = 50, nullable = false)
  private String brand;

  @Column(name = "Model", length = 50, nullable = false)
  private String model;

  @Column(name = "Color", length = 50)
  private String color;

  @Column(name = "NumberOfSeats", nullable = false)
  private Integer numberOfSeats;

  @Column(name = "ProductionYears", nullable = false)
  private LocalDate productionYears;

  @Column(name = "FuelType", length = 50)
  private String fuelType;

  @Column(name = "FuelConsumption" , length = 50, nullable = false)
  private String fuelConsumption;

  @Column(name = "Price", precision = 10, scale = 3, nullable = false) //gia bao hiem = 5% gia xe
  private BigDecimal price;

  @Column(name = "RentalPrice", precision = 10, scale = 3, nullable = false) //giaban
  private BigDecimal rentalPrice;

  @Column(name = "Address", length = 50)
  private String address;

  @Column(name = "Image", length = 10000)
  private String image;

  @Column(name = "TermOfUse", length = 50)
  private String termOfUse;

  @Enumerated(value = EnumType.STRING)
  private CarStatus carStatus;

  @Column(name = "AddressCarStatus", length = 50) //dua di hoac de tu lay
  private Boolean addressCarStatus;

  @Column(name = "VehicleRegistraiton", length = 5000)
  private String vehicleRegistration;

  @Column(name = "AcpCarStatus")
  private Boolean AcpCarStatus;

  @Column(name = "timeBan", length = 255)
  private LocalDate timeBan;

  @Column(name = "reason", length = 255)
  private String reason;

  @OneToMany(mappedBy = "cars")
  private List<Order> orders;

  @OneToMany(mappedBy = "cars")
  private List<Amenitiess> amenitiess;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "User_ID")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OtherAmenities")
  private Amenities amenities;

}