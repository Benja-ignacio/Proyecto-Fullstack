package cl.duoc.logistica.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.logistica.enums.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogisticResponseDTO {
    private Long id; // 
    private Long orderId; // referencia a order service
    private BigDecimal distance; // distancia entre la direccion del usuario y almacen de los productos
    private BigDecimal shipping; // precio del envio
    private Status status; 
    private LocalDateTime expectedDeliveryDate; 
    private LocalDateTime shippedAt; 
    private LocalDateTime deliveredAt;
    private LocalDateTime canceledAt;
}
