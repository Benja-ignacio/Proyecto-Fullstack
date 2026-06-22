package cl.duoc.logistica.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.logistica.model.Logistic;


public interface LogisticRepository extends JpaRepository<Logistic, Long> {
    Optional<Logistic> findByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);

}
