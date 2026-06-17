package cl.duoc.pedido.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.model.Order;
import cl.duoc.pedido.model.OrderItem;

@Component
public class OrderMapper {
    // OrderResponseDTO mapper 
    public OrderResponseDTO toOrderResponseDTOWithItems(Order order, List<OrderItem> orderItems) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .subtotal(order.getSubtotal())
                .discount(order.getDiscount())
                .shipping(order.getShipping())
                .total(order.getTotal())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .paidAt(order.getPaidAt())
                .items(
                        orderItems.stream()
                            .map(this::orderItemToDTO)
                            .toList()
                )
                .build();
    }

    // entity to dto
    public OrderResponseDTO toOrderResponseDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .subtotal(order.getSubtotal())
                .discount(order.getDiscount())
                .shipping(order.getShipping())
                .total(order.getTotal())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .paidAt(order.getPaidAt())
                .build();
    }


    // orderItem to dto
    public OrderItemDTO orderItemToDTO(OrderItem orderItem) {
            return OrderItemDTO.builder()
            .productId(orderItem.getProductId())
            .productName(orderItem.getProductName())
            .price(orderItem.getPrice())
            .quantity(orderItem.getQuantity())
            .build();
        }

    // OrdenItemDTO to entity
    public OrderItem toOrderItemEntity (Long orderId, OrderItemDTO dto) {
        return OrderItem.builder()
        .orderId(orderId)
        .productId(dto.getProductId())
        .productName(dto.getProductName())
        .price(dto.getPrice())
        .quantity(dto.getQuantity())
        .build();
    }
}
