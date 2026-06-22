package cl.duoc.inventario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.inventario.dto.ApiResponse;
import cl.duoc.inventario.dto.InventoryRequestDTO;
import cl.duoc.inventario.dto.InventoryResponseDTO;
import cl.duoc.inventario.dto.InventoryUpdateRequestDTO;
import cl.duoc.inventario.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    
    private final InventoryService inventoryService;

    // crear
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<InventoryResponseDTO>> create (
        @Valid @RequestBody InventoryRequestDTO request) {
        
        InventoryResponseDTO data = inventoryService.create(request);

        ApiResponse<InventoryResponseDTO> response = new ApiResponse<>(
                                            201, "Inventario creado", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // buscar por Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventoryResponseDTO>> getByID (
        @PathVariable Long id) {

        InventoryResponseDTO data = inventoryService.getById(id);

        ApiResponse<InventoryResponseDTO> response = new ApiResponse<>(
                                        200, "Consulta exitosa", data);

        return ResponseEntity.ok(response);
    }

    // buscar por productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<InventoryResponseDTO>> getByProductId(
        @PathVariable Long productId) {

        InventoryResponseDTO data = inventoryService.getByProductId(productId);

        ApiResponse<InventoryResponseDTO> response = new ApiResponse<>(
                                        200, "Consulta exitosa", data);        

        return ResponseEntity.ok(response);
    }

    // buscar todos los inventarios
    @GetMapping()
    public ResponseEntity<ApiResponse<List<InventoryResponseDTO>>> getAll () {
        List<InventoryResponseDTO> data = inventoryService.findAll();

        ApiResponse<List<InventoryResponseDTO>> response = new ApiResponse<>(
                                                                200, "Consulta exitosa", data);
        
        return ResponseEntity.ok(response);
    }

    // actualizar
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<InventoryResponseDTO>> update (
        @PathVariable Long id,
        @Valid @RequestBody InventoryUpdateRequestDTO request) {

        InventoryResponseDTO data = inventoryService.update(id, request);

        ApiResponse<InventoryResponseDTO> response = new ApiResponse<>(
                                                200, "Inventario modificado", data);

        return ResponseEntity.ok(response);
    }

    // eliminar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete (
        @PathVariable Long id) {
        
        inventoryService.delete(id);

        // Cambiado a código 200 para que viaje el cuerpo JSON con tu mensaje de éxito
        ApiResponse<Void> response = new ApiResponse<>(
                                200, "Inventario eliminado", null);
        
        return ResponseEntity.ok(response);
    }
}