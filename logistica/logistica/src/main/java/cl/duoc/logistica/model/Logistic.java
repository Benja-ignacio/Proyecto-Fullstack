package cl.duoc.logistica.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import cl.duoc.logistica.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // 

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
