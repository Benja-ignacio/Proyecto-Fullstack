package cl.duoc.inventario.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", unique = true)
    private Long productId; // referencia a product service

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    // @Column(name = "available_quantity")
    // private Integer availableQuantity;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity;

    public int getAvailableQuantity() {
    return this.totalQuantity - this.reservedQuantity;
    }
}
