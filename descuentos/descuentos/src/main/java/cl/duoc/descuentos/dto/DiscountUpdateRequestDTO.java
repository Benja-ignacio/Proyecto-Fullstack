package cl.duoc.descuentos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.descuentos.enums.ProductType;
import cl.duoc.descuentos.enums.Type;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscountUpdateRequestDTO {
    @NotBlank(message = "la descripcion no puede estar vacia")
    private String description;

    @NotNull(message = "El tipo de descuento no pueda ser nulo")
    private Type type; // PERCENTAGE | FIXED enum

    private ProductType productType; // MOUSE, KEYBOARD, etc

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal minPurchaseAmount;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal maxDiscountAmount;

    // null = no limite
    private Integer usageLimit;

    // null = sin limite
    private Integer usageLimitPerUser;

    // null = sin limite de tiempo
    private LocalDateTime startDate;

    // null = sin limite de tiempo
    private LocalDateTime endDate;

}
