package cl.duoc.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private Long userId; // referencia a user service
    
    private List<OrderItem> items;

    private BigDecimal subtotal; // total de los productos 
    private BigDecimal discount; // descuento
    private BigDecimal shipping; // envio
    private BigDecimal total; // total de subtotal + discount + shipping 
    
    private String status; // crear enum

    private LocalDateTime createdAt;
    private LocalDateTime paidAt;

}



