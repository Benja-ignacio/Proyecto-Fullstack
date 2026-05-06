package cl.duoc.carrito.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {
    private Long id;
    private Long cartId;
    private Long productId; // REFERENCIA EXTERNA
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
