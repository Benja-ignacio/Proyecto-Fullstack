package cl.duoc.pago.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.duoc.pago.dto.PaymentInitRequestDTO;
import cl.duoc.pago.dto.PaymentConfirmRequestDTO;
import cl.duoc.pago.dto.PaymentResponseDTO;
import cl.duoc.pago.enums.Status;
import cl.duoc.pago.exception.custom.PaymentNotFoundException;
import cl.duoc.pago.mappers.PaymentMappers;
import cl.duoc.pago.model.Payment;
import cl.duoc.pago.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMappers mapper;

    // obtener payment por id
    public PaymentResponseDTO getById(Long id) {
        Payment payment = paymentRepository.findById(id)
        .orElseThrow(() -> new PaymentNotFoundException("Error: Pago no encontrado"));

        return mapper.entityToPaymentResponseDTO(payment);
    }

    // obtener payment por orderId
    public PaymentResponseDTO getByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
        .orElseThrow(() -> new PaymentNotFoundException("Error: Pago no encontrado"));

        return mapper.entityToPaymentResponseDTO(payment);
    }

    // crear pago inicial
    public PaymentResponseDTO initPayment(PaymentInitRequestDTO request) { 
        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setUserId(1L); // SIMULADO HASTA INTEGRAR SERVICIOS
        payment.setStatus(Status.PENDING);
        payment.setCardLast4(null);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(null);
        payment.setAmount(new BigDecimal("29990")); // SIMULADO HASTA INTEGRAR SERVICIOS

        paymentRepository.save(payment);

        return mapper.entityToPaymentResponseDTO(payment);
    }

    // actualizar pago
    public PaymentResponseDTO confirmPayment(PaymentConfirmRequestDTO request) {
        Payment payment = paymentRepository.findByOrderId(request.getOrderId())
        .orElseThrow(() -> new PaymentNotFoundException("Error: Pago no encontrado"));
        
        if (request.getCardLast4() == null || request.getCardLast4().length() != 4) {

            payment.setStatus(Status.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Error: Pago rechazado");    
        } 

        payment.setCardLast4(request.getCardLast4());
        payment.setStatus(Status.APPROVED);   
        payment.setTransactionId(UUID.randomUUID().toString());    
        paymentRepository.save(payment);

        return mapper.entityToPaymentResponseDTO(payment);
    }

    // eliminar pago
    public void cancelPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Error: Pago no encontrado"));
        
        payment.setStatus(Status.CANCELED);

        paymentRepository.save(payment);
    }

}
