package org.example.projectspringojt.repository;

import org.example.projectspringojt.entity.Car;
import org.example.projectspringojt.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByOrder_Cars_CarId(Integer carId);
}
