package cl.duoc.pedido.service;

import static org.mockito.Answers.valueOf;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.mapper.OrderMapper;
import cl.duoc.pedido.model.Order;
import cl.duoc.pedido.repository.OrderItemRepository;
import cl.duoc.pedido.repository.OrderRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderServiceTest {
    @Test
        void testGetAllOrders() {
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        OrderMapper mapper = Mockito.mock(OrderMapper.class); 
    OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class); 

        OrderService orderService = new OrderService(orderRepository, orderItemRepository, mapper);

        Order order = new Order(1L, 1L, BigDecimal.valueOf(10000.00), BigDecimal.valueOf(0), BigDecimal.valueOf(4999.00),  BigDecimal.valueOf(14999.00), OrderStatus.PENDING, LocalDateTime.now(), null);
        Mockito.when(orderRepository.findByUserId(1L)).thenReturn(List.of(order));

        List<OrderResponseDTO> result = orderService.getOrdersByUser(1L);

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
    }

}
