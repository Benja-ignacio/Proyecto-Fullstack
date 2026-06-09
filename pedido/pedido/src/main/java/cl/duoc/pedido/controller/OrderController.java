package cl.duoc.pedido.controller;

import cl.duoc.pedido.dto.ApiResponse;
import cl.duoc.pedido.dto.OrderItemDTO;
import cl.duoc.pedido.dto.OrderResponseDTO;
import cl.duoc.pedido.enums.OrderStatus;
import cl.duoc.pedido.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger =
            LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    /**
     * Crear un nuevo pedido
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(
            @RequestParam Long userId,
            @Valid @RequestBody List<OrderItemDTO> items) {

        logger.info("Creando pedido para usuario {}", userId);

        OrderResponseDTO data = orderService.createOrder(userId, items);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Pedido creado correctamente",
                        data
                ));
    }

    /**
     * Obtener historial de pedidos de un usuario
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrders(
            @RequestParam Long userId) {

        logger.info("Consultando pedidos del usuario {}", userId);

        List<OrderResponseDTO> data =
                orderService.getOrdersByUser(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Pedidos obtenidos correctamente",
                        data
                )
        );
    }

    /**
     * Actualizar estado de un pedido
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        logger.info(
                "Actualizando estado del pedido {} a {}",
                orderId,
                status
        );

        OrderResponseDTO order =
                orderService.updateStatus(orderId, status);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Estado actualizado correctamente",
                        order
                )
        );
    }

    /**
     * Obtener detalle de productos de un pedido
     */
    @GetMapping("/{orderId}/items")
    public ResponseEntity<ApiResponse<List<OrderItemDTO>>> getOrderItems(
            @PathVariable Long orderId) {

        logger.info(
                "Consultando items del pedido {}",
                orderId
        );

        List<OrderItemDTO> data =
                orderService.getOrderItems(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Detalle del pedido obtenido correctamente",
                        data
                )
        );
    }

    /**
     * Cancelar pedido
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(
            @PathVariable Long orderId) {

        logger.info(
                "Cancelando pedido {}",
                orderId
        );

        orderService.deleteOrder(orderId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Pedido cancelado correctamente",
                        null
                )
        );
    }
}