package cl.duoc.logistica.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

public class Logistic {
    private Long id; // pk
    private Long orderId; // referencia a order service

    private BigDecimal distance; // distancia entre la direccion del usuario y almacen de los productos
    private BigDecimal shipping; // precio del envio

    private String status; // cambiar  a enum = sent, in wait, canceled

    private LocalDateTime expectedDeliveryDate; 
    private LocalDateTime shippedAt; 
    private LocalDateTime deliveredAt;


}
