package cl.duoc.descuentos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import cl.duoc.descuentos.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "code", nullable = false, unique = true)
    @NotBlank(message = "El codigo no puede estar vacio")
    private String code;

    @NotBlank(message = "la descripcion no puede estar vacia")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "El tipo de descuento no pueda ser nulo")
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type; // PERCENTAGE | FIXED enum

    @Column(name = "product_type", nullable = true) // NULL = DESCUENTO GLOBAL
    @Enumerated(EnumType.STRING)
    private ProductType productType; // MOUSE, KEYBOARD 

    @NotNull(message = "El valor no puede ser nulo")
    @Column(name = "value", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "min_purchase_amount", nullable = false)
    private BigDecimal minPurchaseAmount;

    @NotNull(message = "El maximo descuento permitido no puede ser nulo")
    @Column(name = "max_discount_amount", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal maxDiscountAmount;

    // null = no limite
    @Column(name = "usage_limit", nullable = true)
    private Integer usageLimit;


    @ColumnDefault("0")
    @Min(value = 0)
    @Column(name = "used_count", nullable = false) 
    private Integer usedCount = 0;

    // null = sin limite
    @Column(name = "usage_limit_per_user", nullable = true)
    private Integer usageLimitPerUser;

    // null = sin limite de tiempo
    @Column(name = "start_date", nullable = true)
    private LocalDateTime startDate;

    // null = sin limite de tiempo
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;


    @Column(name = "active", nullable = false)
    private boolean active = true;


}
