package cl.duoc.feedback.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.duoc.feedback.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // busca feedback por id 
    Optional<Feedback> findById(Long id);

    // busca por productId y devuelve la lista de todos los feedback de un producto --> lista vacia si no tiene feedbacks
    List<Feedback> findByProductId(Long productId);

    // devuelve la lista de feedback de un usuario --> devuelve lista vacia si no tiene feedbacks
    List<Feedback> findByUserId(Long userId);

    // busca feedback si existe feedback por id --> devuelve true si existe, false sino
    boolean existsById(Long id);

    // busca si existe feedback por ProductId --> devuelve true si existe, false sino
    boolean existsByProductId(Long productId);

    // toma como parametro userId Y productId se usa para validar si un usuario ya tiene feedback de un producto
    boolean existsByUserIdAndProductId(Long userId, Long productId);

}