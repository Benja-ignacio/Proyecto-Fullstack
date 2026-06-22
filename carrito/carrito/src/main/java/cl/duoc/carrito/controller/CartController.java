package cl.duoc.carrito.controller;

import cl.duoc.carrito.dto.ApiResponse;
import cl.duoc.carrito.dto.CartItemDTO;
import cl.duoc.carrito.dto.CartItemResponseDTO;
import cl.duoc.carrito.dto.CartResponseDTO;
import cl.duoc.carrito.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponseDTO>> addProduct(
            @RequestParam Long userId,
            @Valid @RequestBody CartItemDTO dto) {

        CartItemResponseDTO data = cartService.addProduct(userId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Producto agregado al carrito", data)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDTO>> getCart(
            @RequestParam Long userId) {

        CartResponseDTO data = cartService.getCart(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Carrito obtenido", data)
        );
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable Long itemId) {

        cartService.deleteProduct(itemId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse<CartItemResponseDTO>> updateQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {

        CartItemResponseDTO data = cartService.updateQuantity(itemId, quantity);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Cantidad actualizada", data)
        );
    }

    @GetMapping("/total")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotal(
            @RequestParam Long userId) {

        BigDecimal total = cartService.calculateTotal(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Total calculado", total)
        );
    }
}