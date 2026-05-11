package cl.duoc.pedido.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referencia lógica al microservicio users
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // subtotal de productos
    @Column(nullable = false)
    private BigDecimal subtotal;

    // descuento aplicado
    @Column(nullable = false)
    private BigDecimal discount;

    // costo de envío
    @Column(nullable = false)
    private BigDecimal shipping;

    // total final
    @Column(nullable = false)
    private BigDecimal total;

    // PENDING / PAID / CANCELED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // fecha de creación
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // fecha de pago
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}


