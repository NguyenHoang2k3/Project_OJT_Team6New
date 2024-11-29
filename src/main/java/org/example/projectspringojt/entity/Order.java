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
  private LocalDate OrderStartDate;   // Thời gian nhận xe

  @Column(name = "Order_EndDate", nullable = false)
  private LocalDate OrderEndDate;  // Thời gian trả xe

  @Column(name = "Order_Price", precision = 10, scale = 3,nullable = false)
  private BigDecimal orderPrice;

  @Column(name = "Shipping_Address", nullable = false)
  private String sh_address; // địa điểm nhận


  @Column(name = "reasons", length = 10000)
  private String reasons;


  @Enumerated(value = EnumType.STRING)
  private Status status; //Trạng thái xác nhận nhận xe

  @Enumerated(value = EnumType.STRING)
  private Status statusend;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "User_ID")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Car_ID")
  private Car cars;

  @OneToMany(mappedBy = "order")
  private List<Feedback> feedback;

}
