package cl.duoc.carrito.model;

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
    private Long id;
    private Long cartId;
    private Long productId; // REFERENCIA EXTERNA
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}
