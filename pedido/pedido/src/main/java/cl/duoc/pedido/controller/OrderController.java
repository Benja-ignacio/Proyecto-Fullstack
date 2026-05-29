package cl.duoc.pedido.controller;

import cl.duoc.pedido.dto.ApiResponse;
import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // crear pedido
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(
            @RequestParam Long userId,
            @Valid @RequestBody List<OrderItemDTO> items) {

        OrderResponseDTO data = orderService.createOrder(userId);

        ApiResponse<OrderResponseDTO> response = new ApiResponse<OrderResponseDTO>(201, "pedido creado", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // historial de compras
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrders(
            @RequestParam Long userId) {

        List<OrderResponseDTO> data = orderService.getOrdersByUser(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pedidos obtenidos", data)
        );
    }

    // cambiar estado del pedido
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        OrderResponseDTO order = orderService.updateStatus(orderId, status);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Estado actualizado", order)
        );
    }

    // detalle del pedido
    @GetMapping("/{orderId}/items")
    public ResponseEntity<ApiResponse<List<OrderItemDTO>>> getOrderItems(
            @PathVariable Long orderId) {

        List<OrderItemDTO> data = orderService.getOrderItems(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Detalle del pedido", data)
        );
    }

    // eliminar pedido
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete (
        @PathVariable Long orderID) {
        
        orderService.deleteOrder(orderID);

        ApiResponse<Void> response = new ApiResponse<>(200, "Pedido eliminado", null);

        return ResponseEntity.ok(response);
    }

    
}