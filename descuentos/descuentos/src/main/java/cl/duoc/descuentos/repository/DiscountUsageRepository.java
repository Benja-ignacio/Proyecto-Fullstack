package cl.duoc.descuentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.descuentos.model.DiscountUsage;

public interface DiscountUsageRepository extends JpaRepository<DiscountUsage, Long>{

    List<DiscountUsage> findByUserId(Long userId);
}
