package cl.duoc.pago.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.pago.enums.PaymentMethod;
import cl.duoc.pago.enums.Status;
import cl.duoc.pago.mappers.PaymentMappers;
import cl.duoc.pago.model.Payment;
import cl.duoc.pago.repository.PaymentRepository;

public class PaymentServiceTest {
    @Test
        void cancelPayment() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        PaymentMappers mapper = Mockito.mock(PaymentMappers.class); // ← faltaba este
        PaymentService paymentService = new PaymentService(paymentRepository, mapper);

        Payment payment = new Payment(1L, 1L, 1l, Status.PENDING, "4654", PaymentMethod.CARD, "4586574845985", BigDecimal.valueOf(10000.00),LocalDateTime.now());
        Mockito.when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        paymentService.cancelPayment(1L);

    Mockito.verify(paymentRepository).save(payment);}
}
