package cl.duoc.pedido.service;

import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.model.*;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger =
        LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // crear pedido desde carrito
    public Order createOrder(Long userId, List<OrderItemDTO> itemsDTO) {

        BigDecimal subtotal = itemsDTO.stream()
                .map(item ->
                        item.getPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal shipping = new BigDecimal("5000");
        BigDecimal total = subtotal
                .subtract(discount)
                .add(shipping);

        Order order = new Order(
                null,
                userId,
                subtotal,
                discount,
                shipping,
                total,
                OrderStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        Order savedOrder = orderRepository.save(order);

        for (OrderItemDTO dto : itemsDTO) {
            OrderItem item = new OrderItem(
                    null,
                    savedOrder.getId(),
                    dto.getProductId(),
                    dto.getProductName(),
                    dto.getPrice(),
                    dto.getQuantity()
            );

            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    // ver historial de compras
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // cambiar estado del pedido
    public Order updateStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        order.setStatus(status);

        if (status == OrderStatus.PAID) {
            order.setPaidAt(LocalDateTime.now());
        }

        return orderRepository.save(order);
    }

    // detalle del pedido
    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}