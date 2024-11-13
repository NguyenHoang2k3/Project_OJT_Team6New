package org.example.projectspringojt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "User_ID")
  private Integer userID;

  @Column(name = "User_Name", length = 255, nullable = false)
  private String userName;

  @Column(name = "DateOfBirth")
  private String Dob;

  @Column(name = "National_ID")
  private Integer nationalId;

  @Column(name = "Phone", length = 11, nullable = false)
  private String phone;

  @Column(name = "Email", length = 50, nullable = false)
  private String email;

  @Column(name = "Address", length = 255)
  private String address;

  @Column(name = "Wallet")
  private Integer wallet;

  @Column(name = "Drivinglicense", length = 255)
  private String drivingLicense;

  @Column(name = "Password", length = 255)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(value = EnumType.STRING)
  private StatusUser status;

  @Column(name = "Avatar", length = 5000)
  private String avatar;

  @OneToMany(mappedBy = "user")
  private List<Order> orders = new ArrayList<Order>();

  @OneToMany(mappedBy = "user")
  private List<TicketsDiscount> ticketsDiscounts;

  @OneToMany(mappedBy = "user")
  private List<Car> cars;
}
