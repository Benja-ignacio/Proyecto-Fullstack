package cl.duoc.productos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.productos.enums.Status;
import cl.duoc.productos.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);

    Optional<Product> findBySku(String sku);

    List<Product> findByStatus(Status status);

    List<Product> findByNameContainingIgnoreCase(String name);
}