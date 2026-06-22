package cl.duoc.descuentos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.descuentos.dto.ApiResponse;
import cl.duoc.descuentos.dto.DiscountRequestDTO;
import cl.duoc.descuentos.dto.DiscountResponseDTO;
import cl.duoc.descuentos.dto.DiscountUsageResponseDTO;
import cl.duoc.descuentos.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/discount")
@RequiredArgsConstructor
public class DIscountController {

    private final DiscountService discountService;

    // crear descuento
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DiscountResponseDTO>> create (
        @Valid @RequestBody DiscountRequestDTO request) {
        
        DiscountResponseDTO discount = discountService.create(request);
        
        ApiResponse<DiscountResponseDTO> response = new ApiResponse<DiscountResponseDTO>(
                                        201,"descuento creado", discount);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }    
    
    // actualizar descuento
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DiscountResponseDTO>> update (
        @PathVariable Long id,
        @Valid @RequestBody DiscountRequestDTO requestDTO) {
            
        DiscountResponseDTO discount = discountService.update(id, requestDTO);

        ApiResponse<DiscountResponseDTO> response = new ApiResponse<DiscountResponseDTO>(
                                        200, "descuento modificado", discount);
    
        return ResponseEntity.ok(response);
    }


    // activar / descativar descuento
    @PatchMapping("status/{id}")
    public ResponseEntity<ApiResponse<DiscountResponseDTO>> toggleStatus(
        @PathVariable Long id, boolean active) {
        
        DiscountResponseDTO discount = discountService.toggleStatus(id, active);

        ApiResponse<DiscountResponseDTO> response = new ApiResponse<DiscountResponseDTO>(
                                200,"Consulta exitosa", discount);
        
        return ResponseEntity.ok(response);
    }
    
    //eliminar descuento
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete (
        @PathVariable Long id) {

        discountService.deleteDiscount(id);

        ApiResponse<Void> response = new ApiResponse<Void>(
                        200,"Consulta exitosa", null);

        return ResponseEntity.ok(response);
    }
    
    // buscar descuento por Id
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<DiscountResponseDTO>> getById(
        @PathVariable Long id) {
        
        DiscountResponseDTO discount = discountService.getById(id);

        ApiResponse<DiscountResponseDTO> response = new ApiResponse<DiscountResponseDTO>
                                    (200,"Consulta exitosa", discount);

        return ResponseEntity.ok(response);
    }
    

    // buscar descuentos de un usuario
    @GetMapping("user/{id}")
    public ResponseEntity<ApiResponse<List<DiscountUsageResponseDTO>>> getByUser(
        @PathVariable Long id) {
        
        List<DiscountUsageResponseDTO> discount = discountService.getByUserId(id);

        ApiResponse<List<DiscountUsageResponseDTO>> response = new ApiResponse<List<DiscountUsageResponseDTO>>(
                                        200, "Consulta exitosa", discount);

        return ResponseEntity.ok(response);
    }
    
}
