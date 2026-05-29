package cl.duoc.pago.mappers;

import org.springframework.stereotype.Component;

import cl.duoc.pago.dto.PaymentResponseDTO;
import cl.duoc.pago.model.Payment;

@Component
public class PaymentMappers {

    // Entity to DTO
    public PaymentResponseDTO entityToPaymentResponseDTO(Payment payment) {
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
    public Payment PaymentResponseDTOtoEntity(PaymentResponseDTO dto) {
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
