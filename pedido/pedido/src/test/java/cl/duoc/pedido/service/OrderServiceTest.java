package cl.duoc.pedido.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.mapper.OrderMapper;
import cl.duoc.pedido.model.Order;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;

public class OrderServiceTest {

    @Test
    void testGetAllOrders() {
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        OrderMapper mapper = Mockito.mock(OrderMapper.class);

        OrderService orderService = new OrderService(
                orderRepository,
                orderItemRepository,
                mapper
        );

        Order order = Order.builder()
                .id(1L)
                .userId(1L)
                .subtotal(BigDecimal.valueOf(10000))
                .discount(BigDecimal.ZERO)
                .shipping(BigDecimal.valueOf(4999))
                .total(BigDecimal.valueOf(14999))
                .orderStatus(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        OrderResponseDTO responseDTO = Mockito.mock(OrderResponseDTO.class);

        Mockito.when(orderRepository.findByUserId(1L)).thenReturn(List.of(order));
        Mockito.when(mapper.toOrderResponseDTO(order)).thenReturn(responseDTO);

        List<OrderResponseDTO> result = orderService.getOrdersByUser(1L);

        assertThat(result).hasSize(1);
    }
}