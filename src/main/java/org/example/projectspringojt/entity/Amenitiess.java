package org.example.projectspringojt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Amenitiess")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenitiess {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Amenities_ID")
  private Integer amenitiesId;

  @Column(name = "Amenities_Name", length = 255, nullable = false)
  private String  amenitiesName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Car_id")
  private Car cars;

}