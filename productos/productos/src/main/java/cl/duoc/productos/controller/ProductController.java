package cl.duoc.productos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.productos.dto.ApiResponse;
import cl.duoc.productos.dto.ProductRequestDTO;
import cl.duoc.productos.dto.ProductResponseDTO;
import cl.duoc.productos.dto.UpdateProductRequestDTO;
import cl.duoc.productos.enums.Status;
import cl.duoc.productos.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // privado ROL=ADMIN
    @PostMapping("/create") // crear producto
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(
           @Valid @RequestBody ProductRequestDTO request) {

        ProductResponseDTO data = productService.createProduct(request);

        ApiResponse<ProductResponseDTO> response = new ApiResponse<ProductResponseDTO>
                (200, "Producto creado", data);

        return ResponseEntity.ok(response);
    }

    // PUBLIC NO ROL
    @GetMapping("/{id}") // buscar producto por id 
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getById(@PathVariable Long id) {

        ProductResponseDTO data = productService.getProductById(id);

        ApiResponse<ProductResponseDTO> response = new ApiResponse<ProductResponseDTO>(
                                200, "Consulta exitosa", data);

        return ResponseEntity.ok(response);
    }

    // PUBLIC NO ROL
    @GetMapping("/list") // obtener todos los productos
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll() {
        List<ProductResponseDTO> data = productService.getAllProducts();

        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<List<ProductResponseDTO>>(
                                200,"Lista de productos", data);

        return ResponseEntity.ok(response);
    }

    // PRIVATE ROL=ADMIN
    @PutMapping("/update/{id}") // actualizar producto
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequestDTO request) {

        ProductResponseDTO data = productService.updateProduct(id, request);
    
        ApiResponse<ProductResponseDTO> response = new ApiResponse<ProductResponseDTO>(
                                200, "Producto modificado", data);
            
        return ResponseEntity.ok(response);
    }

    // PRIVATE ROL=ADMIN
    @DeleteMapping("/delete/{id}") // eliminar producto
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        productService.deleteProduct(id);
        ApiResponse<Void> response = new ApiResponse<Void>(
                                200, "Producto deshabilitado correctamente", null);
        
        return ResponseEntity.ok(response);
    }

    // PRIVATE ROL=ADMIN
    @PatchMapping("/update/status/{id}") // modificar status del producto
    public ResponseEntity<ApiResponse<ProductResponseDTO>> changeStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
                
        ProductResponseDTO data = productService.changeStatus(id, status);

        ApiResponse<ProductResponseDTO> response = new ApiResponse<ProductResponseDTO>(
                                200, "Consulta exitosa", data);

        return ResponseEntity.ok(response);
    }
}
