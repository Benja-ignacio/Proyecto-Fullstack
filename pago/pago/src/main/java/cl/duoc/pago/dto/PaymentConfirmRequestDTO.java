package cl.duoc.pago.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentConfirmRequestDTO {

    @NotNull(message = "El orderId es requerido")
    private Long orderId; // referencia a order service

    @NotBlank(message = "Los numeros de la tarjeta son necesarios para el pago")
    @Size(min = 4, max = 4)
    private String cardLast4;

}
