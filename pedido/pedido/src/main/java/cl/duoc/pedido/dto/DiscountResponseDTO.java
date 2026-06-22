package cl.duoc.pedido.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponseDTO {

    private Long id;
    private String code;
    private BigDecimal percentage;
}