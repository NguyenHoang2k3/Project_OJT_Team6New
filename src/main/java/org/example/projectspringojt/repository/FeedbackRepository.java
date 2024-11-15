    package org.example.projectspringojt.repository;

    import org.example.projectspringojt.entity.Feedback;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
        List<Feedback> findByOrder_Cars_CarId(Integer carId);
    }
