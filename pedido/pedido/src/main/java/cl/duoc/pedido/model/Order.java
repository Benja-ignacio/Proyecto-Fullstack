package cl.duoc.pedido.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId; // referencia a user service
    
    @Column(name = "subtotal")
    private BigDecimal subtotal; // total de los productos 

    @Column(name = "discount")
    private BigDecimal discount; // descuento

    @Column(name = "shipping")
    private BigDecimal shipping; // envio

    @Column(name = "total")
    private BigDecimal total; // total de subtotal + discount + shipping 
    
    @Column(name = "status")
    private String status; // crear enum
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
    
    private List<OrderItem> items;
}



