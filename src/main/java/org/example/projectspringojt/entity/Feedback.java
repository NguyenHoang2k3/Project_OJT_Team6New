package org.example.projectspringojt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
@Table(name = "Feedback")
public class Feedback{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Feedback_ID")
  private Integer feedbackId;

  @Column(name = "Ratings", nullable = false)
  private Integer rating;

  @Column(name = "Content", length = 1000, nullable = false)
  private String content;

  @Column(name = "Time", nullable = false)
  private LocalDate time;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Order_ID")
  private Order order;

}
