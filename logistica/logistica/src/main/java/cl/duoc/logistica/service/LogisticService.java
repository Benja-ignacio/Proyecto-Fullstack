package cl.duoc.logistica.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

    public LogisticResponseDTO create(Long orderId, BigDecimal subtotal) {
        // if (orderId exists) -- validar si existe un pedido 

        if (subtotal == null) {
            throw new IllegalArgumentException("El subtotal no puede ser nulo");
        }

        Logistic logistic = new Logistic();
        logistic.setOrderId(orderId);
        logistic.setShipping(calculateShipping(subtotal)); // obtener el precio de envio
        logistic.setStatus(Status.WAITING_PAYMENT);
        logistic.setExpectedDeliveryDate(LocalDateTime.now().plusDays(5));
        logistic.setShippedAt(null); // cambiar a shipped una vez se confirme el pago
        logistic.setDeliveredAt(null);
        
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

    public LogisticResponseDTO changeStatus(Long id, StatusRequestDTO request) {
        Logistic logistic = logisticRepository.findById(id).
            orElseThrow(() -> new LogisticNotFoundException("Logistica con id " + id + " no encontrada"));

        if (logistic.getStatus() == request.getStatus()) {
            throw new IllegalArgumentException("No puedes ingresar el mismo status");
        }

        if (logistic.getStatus() == Status.SHIPPED && request.getStatus() == Status.CANCELED) {
            throw new IllegalArgumentException("No puedes cambiar el estado de Shipped a canceled");
        }

        if (logistic.getStatus() == Status.DELIVERED || logistic.getStatus() == Status.CANCELED) {
            throw new IllegalArgumentException("No puedes cambiar el estado de una logistica finalizada");
        }

        if (request.getStatus() == Status.SHIPPED) {
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

        return mapper.entityToLogisticResponseDTO(logistic);
    }

}
