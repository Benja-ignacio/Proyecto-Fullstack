package cl.duoc.pedido.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItem {
    private Long id; // PK

    @Column(name = "order_id")
    private Long orderId; // relacion a order 

    @Column(name = "product_id")
    private Long productId; // relacion a product service

    @Column(name = "product_name")
    private String productName; 

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;
}
