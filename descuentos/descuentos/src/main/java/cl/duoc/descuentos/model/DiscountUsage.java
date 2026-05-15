package cl.duoc.descuentos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discount_usage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DiscountUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El id de descuento es requerido")
    private Long discountId;

    @NotNull(message = "El id de usuario es requerido")
    private Long userId;

    private LocalDateTime usedAt;

    
}
