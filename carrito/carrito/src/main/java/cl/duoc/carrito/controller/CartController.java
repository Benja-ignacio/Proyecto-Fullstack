package cl.duoc.carrito.controller;

import cl.duoc.carrito.dto.ApiResponse;
import cl.duoc.carrito.dto.CartItemDTO;
import cl.duoc.carrito.model.CartItem;
import cl.duoc.carrito.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CartItem>> addProduct(
            @RequestParam Long userId,
            @Valid @RequestBody CartItemDTO dto) {

        CartItem item = service.addProduct(userId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Producto agregado al carrito", item)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItem>>> getCart(
            @RequestParam Long userId) {

        List<CartItem> items = service.getCart(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Carrito obtenido", items)
        );
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable Long itemId) {

        service.deleteProduct(itemId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Producto eliminado", "OK")
        );
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse<CartItem>> updateQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {

        CartItem item = service.updateQuantity(itemId, quantity);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Cantidad actualizada", item)
        );
    }

    @GetMapping("/total")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotal(
            @RequestParam Long userId) {

        BigDecimal total = service.calculateTotal(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Total calculado", total)
        );
    }
}