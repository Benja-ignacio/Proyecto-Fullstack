package cl.duoc.logistica.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.duoc.logistica.client.NotificationClient;
import cl.duoc.logistica.client.OrderClient;
import cl.duoc.logistica.client.PaymentClient;
import cl.duoc.logistica.client.UserClient;
import cl.duoc.logistica.dto.requests.StatusRequestDTO;
import cl.duoc.logistica.dto.responses.LogisticResponseDTO;
import cl.duoc.logistica.enums.Status;
import cl.duoc.logistica.exception.custom.LogisticNotFoundException;
import cl.duoc.logistica.mapper.LogisticMapper;
import cl.duoc.logistica.model.Logistic;
import cl.duoc.logistica.repository.LogisticRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogisticService {
    private final LogisticRepository logisticRepository;
    private final LogisticMapper mapper; 
    private final OrderClient orderClient;
    private final UserClient userClient;
    private final NotificationClient notificationClient;
    private final PaymentClient paymentClient;

    public BigDecimal calculateShipping(BigDecimal subtotal) {
        BigDecimal minimunRequiredForFreeShipping = BigDecimal.valueOf(50000);
        BigDecimal shippingCost = BigDecimal.valueOf(4999);

        if (subtotal == null) {
            throw new IllegalArgumentException("El subtotal no puede ser null");
        }

        if (subtotal.compareTo(minimunRequiredForFreeShipping) >= 0) {
            return BigDecimal.ZERO;
        }

        return shippingCost;
    }

    public LogisticResponseDTO create(Long orderId, Long userId, BigDecimal subtotal) {
        // Validación real: Comprueba si la orden existe en el otro microservicio
        if (Boolean.FALSE.equals(orderClient.existsByOrderId(orderId))) {
            throw new IllegalArgumentException("No se puede crear la logística: La orden " + orderId + " no existe.");
        }

        // Validación real: Comprueba si el usuario existe en el otro microservicio
        if (Boolean.FALSE.equals(userClient.existsByUserId(userId))) {
            throw new IllegalArgumentException("No se puede crear la logística: El usuario " + userId + " no existe.");
        }

        if (subtotal == null) {
            throw new IllegalArgumentException("El subtotal no puede ser nulo");
        }

        Logistic logistic = new Logistic();
        logistic.setOrderId(orderId);
        logistic.setUserId(userId);
        logistic.setShipping(calculateShipping(subtotal)); 
        logistic.setStatus(Status.WAITING_PAYMENT);
        logistic.setExpectedDeliveryDate(LocalDateTime.now().plusDays(5));
        
        logisticRepository.save(logistic);
        return mapper.entityToLogisticResponseDTO(logistic);
    }

    public LogisticResponseDTO getById(Long id) {
        Logistic logistic = logisticRepository.findById(id).
            orElseThrow(() -> new LogisticNotFoundException("Logistica con id " + id + " no encontrada"));
        
        return mapper.entityToLogisticResponseDTO(logistic);
    }

    public List<LogisticResponseDTO> getAll() {
        return logisticRepository.findAll()
            .stream()
            .map(mapper::entityToLogisticResponseDTO)
            .collect(Collectors.toList());
    }

    public LogisticResponseDTO changeStatus(Long orderId, StatusRequestDTO request) {
        Logistic logistic = logisticRepository.findByOrderId(orderId).
            orElseThrow(() -> new LogisticNotFoundException("Logistica con orderId " + orderId + " no encontrada"));

        if (logistic.getStatus() == request.getStatus()) {
            throw new IllegalArgumentException("No puedes ingresar el mismo status");
        }

        if (logistic.getStatus() == Status.SHIPPED && request.getStatus() == Status.CANCELED) {
            throw new IllegalArgumentException("No puedes cambiar el estado de Shipped a canceled");
        }

        if (logistic.getStatus() == Status.DELIVERED || logistic.getStatus() == Status.CANCELED) {
            throw new IllegalArgumentException("No puedes cambiar el estado de una logistica finalizada");
        }

        // Valida el pago con el microservicio de pagos
        if (request.getStatus() == Status.SHIPPED) {
            Boolean isPaid = paymentClient.isOrderPaid(logistic.getOrderId());
            if (Boolean.FALSE.equals(isPaid)) {
                throw new IllegalStateException("No se puede despachar: La orden #" + logistic.getOrderId() + " no registra un pago aprobado.");
            }
            logistic.setShippedAt(LocalDateTime.now());
        }

        if (request.getStatus() == Status.DELIVERED) {
            logistic.setDeliveredAt(LocalDateTime.now());
        }

        if (request.getStatus() == Status.CANCELED) {
            logistic.setCanceledAt(LocalDateTime.now());
        }

        logistic.setStatus(request.getStatus());
        logisticRepository.save(logistic);

        // Envía la notificación alertando el cambio de estado
        notificationClient.sendStatusUpdateNotification(
            logistic.getUserId(), 
            logistic.getOrderId(), 
            request.getStatus().name()
        );

        return mapper.entityToLogisticResponseDTO(logistic);
    }
}