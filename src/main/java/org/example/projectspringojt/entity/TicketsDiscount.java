package org.example.projectspringojt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TicketsDisccount")
public class TicketsDiscount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Tickets_ID")
  private Integer ticketsId;

  @Column(name = "TicketsName", length = 50, nullable = false)
  private String ticketsName;

  @Column(name = "Discount", nullable = false)
  private double discount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "User_ID")
  private User user;

}
