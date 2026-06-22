package cl.duoc.descuentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.descuentos.model.DiscountUsage;

<<<<<<< HEAD
public interface DiscountUsageRepository extends JpaRepository<DiscountUsage, Long >{

    Optional<DiscountUsage> findById(Long id);
=======
public interface DiscountUsageRepository extends JpaRepository<DiscountUsage, Long>{
>>>>>>> benja

    List<DiscountUsage> findByUserId(Long userId);
}
