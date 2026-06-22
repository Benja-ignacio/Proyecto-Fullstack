package cl.duoc.pago.dto;

import cl.duoc.pago.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentInitRequestDTO {
    @NotNull(message = "El orderId es requerido")
    private Long orderId;

    private PaymentMethod paymentMethod;

}
