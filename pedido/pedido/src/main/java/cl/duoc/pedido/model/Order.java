package cl.duoc.pedido.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.pedido.enums.OrderStatus;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referencia lógica al microservicio users
    @NotNull(message = "El userId es requerido")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // subtotal de productos
    @NotNull(message = "El subototal no puede ser nulo")
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    // descuento aplicado
    @NotNull(message = "El descuento no puede ser nulo")
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    // costo de envío
    @NotNull(message = "El envio no puede ser nulo")
    @Column(name = "shipping", nullable = false)
    private BigDecimal shipping;

    // total final
    @Column(name = "total", nullable = false)
    @NotNull(message = "El total no puede ser nulo")
    private BigDecimal total;

    // PENDING / PAID / CANCELED
    @NotNull(message = "OrderStatus no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    // fecha de creación
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // fecha de pago
    @Column(name = "paid_at")
    private LocalDateTime paidAt;


    /* @PrePersist se utiliza para marcar un metodo que debe ejecutarse automaticamente 
    antes de que una entidad se guarde(persista) por primera vez en la base de datos
    */
    @PrePersist 
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}


