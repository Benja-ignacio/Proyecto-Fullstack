package cl.duoc.pedido.model;

import java.math.BigDecimal;

public class OrderItem {
    private Long id; // PK

    private Long orderId; // relacion a order 
    private Long productId; // relacion a product service

    private String productName; 
    private BigDecimal price;
    private Integer quantity;
}
