package cl.duoc.pedido.service;

import cl.duoc.pedido.Client.CartClient;
import cl.duoc.pedido.Client.DiscountClient;
import cl.duoc.pedido.Client.LogisticClient;
import cl.duoc.pedido.Client.PaymentClient;
import cl.duoc.pedido.Client.ProductClient;
import cl.duoc.pedido.Client.UserClient;
import cl.duoc.pedido.dto.CartItemResponse;
import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.exception.custom.OrderResourceNotFoundException;
import cl.duoc.pedido.model.Order;
import cl.duoc.pedido.model.OrderItem;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    private final UserClient userClient;
    private final CartClient cartClient;
    private final ProductClient productClient;
    private final DiscountClient discountClient;
    private final LogisticClient logisticClient;
    private final PaymentClient paymentClient;

    public OrderResponseDTO createOrder(Long userId, List<OrderItemDTO> itemsDTO) {

        logger.info("Creando pedido para usuario {}", userId);

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("El id del usuario es inválido");
        }

        if (itemsDTO == null || itemsDTO.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe contener al menos un producto");
        }

        // Validar usuario en microservicio usuarios
        userClient.getUser(userId);

        // Consultar carrito en microservicio carrito
        List<CartItemResponse> cartItems = cartClient.getCartItems(userId);

        if (cartItems == null || cartItems.isEmpty()) {
            logger.warn("El carrito del usuario {} está vacío", userId);
        }

        // Validar productos en microservicio productos
        for (OrderItemDTO item : itemsDTO) {

            if (item.getProductId() == null || item.getProductId() <= 0) {
                throw new IllegalArgumentException("El id del producto es inválido");
            }

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
            }

            if (item.getPrice() == null ||
                    item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio debe ser mayor a 0");
            }

            productClient.getProduct(item.getProductId());
        }

        BigDecimal subtotal = calculateSubtotal(itemsDTO);

        // Consultar descuento, por ahora solo valida comunicación
        discountClient.getDiscount(userId);
        BigDecimal discount = BigDecimal.ZERO;

        BigDecimal shipping = new BigDecimal("5000");

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

        Order savedOrder = orderRepository.save(newOrder);

        List<OrderItem> items = itemsDTO.stream()
                .map(dto -> toOrderItemEntity(savedOrder.getId(), dto))
                .toList();

        orderItemRepository.saveAll(items);

        // Consultar logística y pago después de crear pedido
        logisticClient.calculateShipping(savedOrder.getId());
        paymentClient.createPayment(savedOrder.getId());

        logger.info("Pedido {} creado correctamente", savedOrder.getId());

        return toOrderResponseDTOWithItems(savedOrder, items);
    }

    private BigDecimal calculateSubtotal(List<OrderItemDTO> itemsDTO) {
        return itemsDTO.stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderResponseDTO> getOrdersByUser(Long userId) {

        logger.info("Consultando pedidos del usuario {}", userId);

        List<Order> list = orderRepository.findByUserId(userId);

        return list.stream()
                .map(this::toOrderResponseDTO)
                .toList();
    }

    public OrderResponseDTO updateStatus(Long orderId, OrderStatus status) {

        logger.info("Actualizando pedido {} a estado {}", orderId, status);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderResourceNotFoundException("Pedido no encontrado"));

        order.setOrderStatus(status);

        if (status == OrderStatus.PAID) {
            order.setPaidAt(LocalDateTime.now());
            logger.info("Pedido {} marcado como PAGADO", orderId);
        }

        Order savedOrder = orderRepository.save(order);

        return toOrderResponseDTO(savedOrder);
    }

    public List<OrderItemDTO> getOrderItems(Long orderId) {

        List<OrderItem> items =
                orderItemRepository.findByOrderId(orderId);

        if (items.isEmpty()) {
            logger.warn("No se encontraron items para pedido {}", orderId);
            throw new OrderResourceNotFoundException(
                    "No se encontraron items para el pedido");
        }

        return items.stream()
                .map(this::orderItemToDTO)
                .toList();
    }

    public void deleteOrder(Long orderID) {

        Order order = orderRepository.findById(orderID)
                .orElseThrow(() ->
                        new OrderResourceNotFoundException("No se encontró pedido"));

        order.setOrderStatus(OrderStatus.CANCELED);

        orderRepository.save(order);

        logger.warn("Pedido {} cancelado", orderID);
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
                .items(orderItems.stream()
                        .map(this::orderItemToDTO)
                        .toList())
                .build();
    }

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

    public OrderItemDTO orderItemToDTO(OrderItem orderItem) {

        return OrderItemDTO.builder()
                .productId(orderItem.getProductId())
                .productName(orderItem.getProductName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public OrderItem toOrderItemEntity(Long orderId, OrderItemDTO dto) {

        return OrderItem.builder()
                .orderId(orderId)
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}