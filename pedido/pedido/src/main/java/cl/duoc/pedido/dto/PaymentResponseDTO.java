package cl.duoc.pedido.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long paymentId;
    private String status;
    private String message;
}