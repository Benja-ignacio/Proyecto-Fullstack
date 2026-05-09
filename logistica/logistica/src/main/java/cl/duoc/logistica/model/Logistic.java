package cl.duoc.logistica.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import cl.duoc.logistica.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Logistic {
    private Long id; // pk

    @Column(name = "order_id")
    private Long orderId; // referencia a order service

    @Column(name = "distance")
    private BigDecimal distance; // distancia entre la direccion del usuario y almacen de los productos

    @Column(name = "shipping")
    private BigDecimal shipping; // precio del envio

    @Column(name = "status")
    private Status status; // cambiar  a enum = sent, in wait, canceled

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate; 

    @Column(name = "shipped_at")
    private LocalDateTime shippedAt; 

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;


}
