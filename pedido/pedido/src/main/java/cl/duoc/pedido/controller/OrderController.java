package cl.duoc.pedido.controller;

import cl.duoc.pedido.dto.ApiResponse;
import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.model.Order;
import cl.duoc.pedido.model.OrderItem;
import cl.duoc.pedido.model.OrderStatus;
import cl.duoc.pedido.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // crear pedido
    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(
            @RequestParam Long userId,
            @RequestBody List<OrderItemDTO> items) {

        Order order = service.createOrder(userId, items);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pedido creado correctamente", order)
        );
    }

    // historial de compras
    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getOrders(
            @RequestParam Long userId) {

        List<Order> orders = service.getOrdersByUser(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pedidos obtenidos", orders)
        );
    }

    // cambiar estado
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Order>> updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        Order order = service.updateStatus(orderId, status);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Estado actualizado", order)
        );
    }

    // detalle del pedido
    @GetMapping("/{orderId}/items")
    public ResponseEntity<ApiResponse<List<OrderItem>>> getOrderItems(
            @PathVariable Long orderId) {

        List<OrderItem> items = service.getOrderItems(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Detalle del pedido", items)
        );
    }
}