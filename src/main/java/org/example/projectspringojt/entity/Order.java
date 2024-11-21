package org.example.projectspringojt.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Order_ID")
  private Integer orderId;

  @Column(name = "Order_StartDate", nullable = false)
  private LocalDate OrderStartDate;

  @Column(name = "Order_EndDate", nullable = false)
  private LocalDate OrderEndDate;

<<<<<<< HEAD
  @Enumerated(value = EnumType.STRING)
  private Status Status;
=======
  @Column(name = "Order_Price", precision = 10, scale = 3,nullable = false)
  private BigDecimal orderPrice;

  @Column(name = "Shipping_Address", nullable = false)
  private String sh_address;

  @Enumerated(value = EnumType.STRING)
  private Status status;
>>>>>>> 2999dd8348e6735ec83ba4e7ace7671c8df18cd1

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "User_ID")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Car_ID")
  private Car cars;

  @OneToMany(mappedBy = "order")
  private List<Feedback> feedback;

}
