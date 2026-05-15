package cl.duoc.descuentos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.descuentos.enums.ProductType;
import cl.duoc.descuentos.enums.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountResponseDTO {

    @NotBlank(message = "El codigo no puede estar vacio")
    private String code;

    @NotBlank(message = "la descripcion no puede estar vacia")
    private String description;

    @NotNull(message = "El tipo de descuento no pueda ser nulo")
    private Type type; // PERCENTAGE | FIXED enum

    @NotNull(message = "El tipo de producto no puede ser nulo")
    private ProductType productType; // MOUSE, KEYBOARD

    @NotNull(message = "El valor no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal minPurchaseAmount;

    @NotNull(message = "El maximo descuento permitido no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal maxDiscountAmount;

    // null = no limite
    private Integer usageLimit;


    private Integer usedCount = 0;

    // null = sin limite
    private Integer usageLimitPerUser;

    // null = sin limite de tiempo
    private LocalDateTime startDate;

    // null = sin limite de tiempo
    private LocalDateTime endDate;

    private boolean active = false;
}
