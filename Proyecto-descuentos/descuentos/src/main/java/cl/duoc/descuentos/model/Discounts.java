package cl.duoc.descuentos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "discounts")

public class Discounts {
    private Long id;
    private String code;
    private String description;

    private String type; // PERCENTAGE | FIXED enum
    private String product_type; // enum 
    private BigDecimal value;

    private BigDecimal minPurchaseAmount;
    private BigDecimal maxDiscountAmount;

    private Integer usageLimit;
    private Integer usedCount;
    private Integer usageLimitPerUser;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private boolean active;
}
