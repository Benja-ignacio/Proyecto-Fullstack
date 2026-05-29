package cl.duoc.pedido.service;

import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.exception.custom.OrderResourceNotFoundException;
import cl.duoc.pedido.mapper.OrderMapper;
import cl.duoc.pedido.model.*;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper mapper;

    /**
    * Crea una orden a partir de los ítems del carrito de un usuario.
    * El descuento y el envío son valores temporales hasta integrar
    * los servicios de descuentos y logística.
    *
    * @param userId   ID del usuario que realiza el pedido
    * @return DTO con la orden creada e ítems persistidos
    */
    public OrderResponseDTO createOrder(Long userId) {
        
        List<OrderItemDTO> itemsDTO = List.of(); // lista vacía temporal hasta integrar carrito

        BigDecimal subtotal = calculateSubtotal(itemsDTO);
        BigDecimal discount = BigDecimal.ZERO; // // TODO: integrar servicio de discount
        BigDecimal shipping = new BigDecimal("5000"); // TODO: integrar servicio de logistic
        
        BigDecimal total = subtotal
        .subtract(discount)
        .add(shipping);
        
        
        // usar builder en vez de constructor normal para mejor legibilidad y no tener constructores tan largos
        // NOTA los atributos no añadidos quedaran null por defecto
        Order newOrder = Order.builder()
        .userId(userId)
        .subtotal(subtotal)
        .discount(discount)
                .shipping(shipping)
                .total(total)
                .orderStatus(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(newOrder);
        
        // TODO: integrar CartService → List<OrderItemDTO> items = cartService.getCartItems(userId)

        // transformar lista de OrderItemDTO a lista de tipo OrderItem
        List<OrderItem> items = itemsDTO.stream()
                            .map(dto -> mapper.toOrderItemEntity(savedOrder.getId(), dto))
                            .toList();

        /*
        * saveAll persiste una colección de entidades en la base de datos
        * internamente itera los elementos y ejecuta persistencia por cada uno
        * puede ser más eficiente que hacer save() en un loop manual
        * documentar en explicaciones.md
        */
        orderItemRepository.saveAll(items);

        return mapper.toOrderResponseDTOWithItems(savedOrder, items);
    }

    // calcular el subtotal de una lista de items
    private BigDecimal calculateSubtotal(List<OrderItemDTO> itemsDTO) {
    return itemsDTO.stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

    // ver historial de compras
    public List<OrderResponseDTO> getOrdersByUser(Long userId) {
        List<Order> list = orderRepository.findByUserId(userId);

        return list.stream()
               .map(mapper::toOrderResponseDTO)
               .toList();
    }

    // cambiar estado del pedido
    public OrderResponseDTO updateStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderResourceNotFoundException("Pedido no encontrado"));

        order.setOrderStatus(status);

        if (status == OrderStatus.PAID) {
            order.setPaidAt(LocalDateTime.now());
        }

        Order savedOrder = orderRepository.save(order);  // también faltaba persistir

        return mapper.toOrderResponseDTO(savedOrder);
    }

    // detalle del pedido
    public List<OrderItemDTO> getOrderItems(Long orderId) {
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        if (items.isEmpty()) {
            throw new OrderResourceNotFoundException("No se encontraron items para el pedido");
            }

        return items.stream()
                .map(mapper::orderItemToDTO)
                .toList();
        }

    
    // eliminar pedido
    public void deleteOrder(Long orderID) {
        Order order = orderRepository.findById(orderID)
                        .orElseThrow(() -> new OrderResourceNotFoundException("No se encontro pedido"));

        order.setOrderStatus(OrderStatus.CANCELED);

        // TODO: implementar soft delete (campo deleted + deletedAt en entidad Order)
        orderRepository.save(order);
    }
}