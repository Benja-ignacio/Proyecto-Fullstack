package cl.duoc.descuentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.descuentos.enums.ProductType;
import cl.duoc.descuentos.enums.Type;
import cl.duoc.descuentos.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findById(Long id);

    Optional<Discount> findByType(Type type);

    Optional<Discount> findByProductType(ProductType productType);

    boolean existsById(Long id);

    List<Discount> findByActive(boolean active); // true buscar todos los descuentos activos, false todos los inactivos

    boolean existsByCode(String code);
}
