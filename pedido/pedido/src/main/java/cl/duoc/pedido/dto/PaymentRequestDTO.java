package cl.duoc.pedido.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    private Long orderId;
    private Long userId;
    private BigDecimal amount;
}
