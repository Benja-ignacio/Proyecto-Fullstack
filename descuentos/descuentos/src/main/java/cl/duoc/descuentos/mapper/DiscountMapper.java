package cl.duoc.descuentos.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.descuentos.dto.DiscountResponseDTO;
import cl.duoc.descuentos.dto.DiscountUsageResponseDTO;
import cl.duoc.descuentos.model.Discount;
import cl.duoc.descuentos.model.DiscountUsage;

@Component
public class DiscountMapper {
        // entity to dto de discounts 
    public DiscountResponseDTO DiscountToDTO(Discount discount) {
        return DiscountResponseDTO.builder()
            .code(discount.getCode())
            .description(discount.getDescription())
            .type(discount.getType())
            .productType(discount.getProductType())
            .value(discount.getValue())
            .minPurchaseAmount(discount.getMinPurchaseAmount())
            .maxDiscountAmount(discount.getMaxDiscountAmount())
            .usageLimit(discount.getUsageLimit())
            .usageLimitPerUser(discount.getUsageLimitPerUser())
            .startDate(discount.getStartDate())
            .endDate(discount.getEndDate())
            .active(discount.isActive())
            .build();
    }

    // entity to dto de discountUsage
    public DiscountUsageResponseDTO DiscountUsageToDTO(DiscountUsage discountUsage) {
        return DiscountUsageResponseDTO.builder()
            .id(discountUsage.getId())
            .discountId(discountUsage.getDiscountId())
            .userId(discountUsage.getUserId())
            .usedAt(discountUsage.getUsedAt())
            .build();
    }

}
