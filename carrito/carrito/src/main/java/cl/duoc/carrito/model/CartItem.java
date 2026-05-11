package cl.duoc.carrito.model;

import java.lang.annotation.Inherited;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId; // REFERENCIA EXTERNA

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;
}
