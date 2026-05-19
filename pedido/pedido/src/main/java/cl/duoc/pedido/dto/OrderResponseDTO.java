package cl.duoc.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import cl.duoc.pedido.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal shipping;
    private BigDecimal total;

    // pending por default
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime paidAt;

    private List<OrderItemDTO> items;

}
