package cl.duoc.pedido.service;

import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.exception.custom.OrderResourceNotFoundException;
import cl.duoc.pedido.model.*;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger =
            LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderResponseDTO createOrder(
            Long userId,
            List<OrderItemDTO> itemsDTO) {

        logger.info("Creando pedido para usuario {}", userId);

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException(
                    "El id del usuario es inválido");
        }

        if (itemsDTO == null || itemsDTO.isEmpty()) {
            throw new IllegalArgumentException(
                    "El pedido debe contener al menos un producto");
        }

        BigDecimal subtotal = calculateSubtotal(itemsDTO);

        BigDecimal discount = BigDecimal.ZERO;

        BigDecimal shipping =
                new BigDecimal("5000");

        BigDecimal total = subtotal
                .subtract(discount)
                .add(shipping);

        Order newOrder = Order.builder()
                .userId(userId)
                .subtotal(subtotal)
                .discount(discount)
                .shipping(shipping)
                .total(total)
                .orderStatus(OrderStatus.PENDING)
                .build();

        Order savedOrder =
                orderRepository.save(newOrder);

        List<OrderItem> items = itemsDTO.stream()
                .map(dto ->
                        toOrderItemEntity(
                                savedOrder.getId(),
                                dto))
                .toList();

        orderItemRepository.saveAll(items);

        logger.info(
                "Pedido {} creado correctamente",
                savedOrder.getId());

        return toOrderResponseDTOWithItems(
                savedOrder,
                items);
    }

    private BigDecimal calculateSubtotal(
            List<OrderItemDTO> itemsDTO) {

        return itemsDTO.stream()
                .map(item ->
                        item.getPrice().multiply(
                                BigDecimal.valueOf(
                                        item.getQuantity())))
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add);
    }

    public List<OrderResponseDTO> getOrdersByUser(
            Long userId) {

        logger.info(
                "Consultando pedidos del usuario {}",
                userId);

        List<Order> list =
                orderRepository.findByUserId(userId);

        return list.stream()
                .map(this::toOrderResponseDTO)
                .toList();
    }

    public OrderResponseDTO updateStatus(
            Long orderId,
            OrderStatus status) {

        logger.info(
                "Actualizando pedido {} a estado {}",
                orderId,
                status);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderResourceNotFoundException(
                                "Pedido no encontrado"));

        order.setOrderStatus(status);

        if (status == OrderStatus.PAID) {

            order.setPaidAt(
                    LocalDateTime.now());

            logger.info(
                    "Pedido {} marcado como PAGADO",
                    orderId);
        }

        Order savedOrder =
                orderRepository.save(order);

        return toOrderResponseDTO(savedOrder);
    }

    public List<OrderItemDTO> getOrderItems(
            Long orderId) {

        List<OrderItem> items =
                orderItemRepository.findByOrderId(orderId);

        if (items.isEmpty()) {

            logger.warn(
                    "No se encontraron items para pedido {}",
                    orderId);

            throw new OrderResourceNotFoundException(
                    "No se encontraron items para el pedido");
        }

        return items.stream()
                .map(this::orderItemToDTO)
                .toList();
    }

    public void deleteOrder(Long orderID) {

        Order order =
                orderRepository.findById(orderID)
                        .orElseThrow(() ->
                                new OrderResourceNotFoundException(
                                        "No se encontró pedido"));

        order.setOrderStatus(
                OrderStatus.CANCELED);

        orderRepository.save(order);

        logger.warn(
                "Pedido {} cancelado",
                orderID);
    }

    public OrderResponseDTO toOrderResponseDTOWithItems(
            Order order,
            List<OrderItem> orderItems) {

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

    public OrderResponseDTO toOrderResponseDTO(
            Order order) {

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

    public OrderItemDTO orderItemToDTO(
            OrderItem orderItem) {

        return OrderItemDTO.builder()
                .productId(orderItem.getProductId())
                .productName(orderItem.getProductName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public OrderItem toOrderItemEntity(
            Long orderId,
            OrderItemDTO dto) {

        return OrderItem.builder()
                .orderId(orderId)
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}