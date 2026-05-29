package cl.duoc.pago.mappers;

import cl.duoc.pago.dto.PaymentResponseDTO;
import cl.duoc.pago.model.Payment;
import lombok.Data;

@Data
public class PaymentMappers {

    // Entity to DTO
    public PaymentResponseDTO entityToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
        .id(payment.getId())
        .orderId(payment.getOrderId())
        .userId(payment.getUserId())
        .status(payment.getStatus())
        .cardLast4(payment.getCardLast4())
        .paymentMethod(payment.getPaymentMethod())
        .transactionId(payment.getTransactionId())
        .amount(payment.getAmount())
        .createdAt(payment.getCreatedAt())
        .build();
    }

    // DTO to entity
    public Payment DTOtoEntity(PaymentResponseDTO dto) {
        return Payment.builder()
        .id(dto.getId())
        .orderId(dto.getOrderId())
        .userId(dto.getUserId())
        .status(dto.getStatus())
        .cardLast4(dto.getCardLast4())
        .paymentMethod(dto.getPaymentMethod())
        .transactionId(dto.getTransactionId())
        .amount(dto.getAmount())
        .build();
    }

}
