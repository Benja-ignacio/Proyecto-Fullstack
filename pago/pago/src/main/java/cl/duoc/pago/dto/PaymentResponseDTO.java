package cl.duoc.pago.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.duoc.pago.enums.PaymentMethod;
import cl.duoc.pago.enums.Status;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long id;

    @NotNull(message = "El orderId es requerido")
    private Long orderId; // referencia a order service

    @NotNull(message = "El userId es requerido")
    private Long userId; // Referencia a user service

    @NotNull(message = "El status es requerido")
    private Status status; // FAILED, APPROVED, PENDING

    @NotBlank(message = "Los numeros de la tarjeta son necesarios para el pago")
    @Size(min = 4, max = 4)
    private String cardLast4;

    @NotNull(message = "EL tipo de pago es necesario")
    private PaymentMethod paymentMethod; // CARD, TRANSFER

    @NotBlank(message = "La id de transaccion es requerida")
    private String transactionId;
    
    @DecimalMin(value = "0.0", message = "La cantidad no puede ser menor a 0")
    private BigDecimal amount;

    @NotNull(message = "createdAt es necesario")
    private LocalDateTime createdAt;
}
