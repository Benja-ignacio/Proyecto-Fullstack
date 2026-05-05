package cl.duoc.carrito.controller;

import cl.duoc.carrito.dto.ApiResponse;
import cl.duoc.carrito.model.Cart;
import cl.duoc.carrito.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<Cart>> create(
            @Valid @RequestBody Cart cart) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Producto agregado correctamente",
                        service.save(cart)
                )
        );
    }

    // List all
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cart>>> getAll() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Listado del carrito",
                        service.findAll()
                )
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Producto eliminado correctamente",
                        "OK"
                )
        );
    }

    // Update quantity
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Cart>> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Cantidad actualizada correctamente",
                        service.updateQuantity(id, quantity)
                )
        );
    }

    // Total cart
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<Double>> totalCart() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Total del carrito calculado correctamente",
                        service.getTotalCart()
                )
        );
    }
}