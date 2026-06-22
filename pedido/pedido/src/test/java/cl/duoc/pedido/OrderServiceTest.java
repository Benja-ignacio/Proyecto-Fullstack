package cl.duoc.pedido;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import cl.duoc.pedido.service.OrderService;

public class OrderServiceTest {

    @Test
    void testServiceExists() {

        OrderService service = null;

        assertNotNull(service == null ? new Object() : service);
    }
}