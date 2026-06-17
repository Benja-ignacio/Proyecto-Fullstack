package cl.duoc.logistica.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.logistica.dto.responses.LogisticResponseDTO;
import cl.duoc.logistica.model.Logistic;
import lombok.Data;

@Data

@Component
public class LogisticMapper {
    public LogisticResponseDTO entityToLogisticResponseDTO(Logistic logistic) {
        return LogisticResponseDTO.builder()
            .id(logistic.getId())
            .orderId(logistic.getOrderId())
            .distance(logistic.getDistance())
            .shipping(logistic.getShipping())
            .status(logistic.getStatus())
            .expectedDeliveryDate(logistic.getExpectedDeliveryDate())
            .shippedAt(logistic.getShippedAt())
            .deliveredAt(logistic.getDeliveredAt())
            .canceledAt(logistic.getCanceledAt())
            .build();
    }
}