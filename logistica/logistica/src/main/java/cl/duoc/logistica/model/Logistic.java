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
    private Long id; // 

    @Column(name = "order_id", nullable = false)
    private Long orderId; // referencia a order service

    @Column(name = "user_id", nullable = false)
    private Long userId; // referencia a 


    /**
     * Distancia entre el almacén y la dirección de entrega.
     *
     * Actualmente no se utiliza.
     * Se deja para una futura implementación de cálculo de envío basado en distancia.
     */
    @Column(name = "distance")
    private BigDecimal distance; // distancia entre la direccion del usuario y almacen de los productos

    @Column(name = "shipping", nullable = false)
    private BigDecimal shipping; // precio del envio

    @Column(name = "status", nullable = false)
    private Status status; // cambiar  a enum = sent, in wait, canceled

    @Column(name = "expected_delivery_date", nullable = false)
    private LocalDateTime expectedDeliveryDate; 

    @Column(name = "shipped_at")
    private LocalDateTime shippedAt; 

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

}
