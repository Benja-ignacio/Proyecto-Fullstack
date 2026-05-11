package cl.duoc.pedido.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK interna con orders
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    // referencia lógica al microservicio products
    @Column(name = "product_id", nullable = false)
    private Long productId;

    // snapshot del nombre del producto
    @Column(name = "product_name", nullable = false)
    private String productName;

    // snapshot del precio
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;
}