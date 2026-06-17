package cl.duoc.descuentos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountUsageResponseDTO {
    private Long id;
    private Long discountId;
    private Long userId;
    private LocalDateTime usedAt;
}
