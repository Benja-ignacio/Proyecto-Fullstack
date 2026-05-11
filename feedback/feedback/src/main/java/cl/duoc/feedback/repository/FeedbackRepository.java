package cl.duoc.feedback.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.feedback.model.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByProductId(Long productId);

    List<Feedback> findByUserId(Long userId);

    boolean existsByUserIdAndProductId(Long userId, Long productId);
}