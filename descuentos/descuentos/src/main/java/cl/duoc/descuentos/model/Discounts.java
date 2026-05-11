package cl.duoc.descuentos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.descuentos.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "discounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Discounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Type type; // PERCENTAGE | FIXED enum

    @Column(name = "product_type")
    private ProductType productType; // MOUSE, KEYBOARD

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "min_purchase_amount")
    private BigDecimal minPurchaseAmount;

    @Column(name = "max_purchase_amount")
    private BigDecimal maxDiscountAmount;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "used_count")
    private Integer usedCount;

    @Column(name = "usage_limit_per_user")
    private Integer usageLimitPerUser;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "active")
    private boolean active;
}
