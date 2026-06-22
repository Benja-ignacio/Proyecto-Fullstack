package cl.duoc.logistica.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.logistica.dto.requests.StatusRequestDTO;
import cl.duoc.logistica.dto.responses.ApiResponse;
import cl.duoc.logistica.dto.responses.LogisticResponseDTO;
import cl.duoc.logistica.service.LogisticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logistics")
public class LogisticController {
    private final LogisticService logisticService;

    @GetMapping("/shipping/{subtotal}")    
    public ResponseEntity<ApiResponse<BigDecimal>> calculateShipping(
        @PathVariable BigDecimal subtotal) {
        BigDecimal Subtotal = logisticService.calculateShipping(subtotal);

        return ResponseEntity.ok(
            new ApiResponse<>(200,"Total del envio", Subtotal));
    }


    @PostMapping("/{orderId}")
    public ResponseEntity<ApiResponse<LogisticResponseDTO>> create (
        @PathVariable Long orderId,
        @RequestParam Long userId, 
        @RequestParam BigDecimal subtotal) {

        LogisticResponseDTO logistic = logisticService.create(orderId, userId, subtotal);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(201, "Logistica creada", logistic));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<LogisticResponseDTO>> getById(
        @PathVariable Long id) {
        LogisticResponseDTO logistic = logisticService.getById(id);

        return ResponseEntity.ok(new ApiResponse<>(
            200, "Logistica encontrada", logistic));
    }
    
    @GetMapping()
    public ResponseEntity<ApiResponse<List<LogisticResponseDTO>>> getAll() {
        List<LogisticResponseDTO> data = logisticService.getAll();

        return ResponseEntity.ok(
            new ApiResponse<>(200, "Lista de logisticas", data));
    }
    
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<LogisticResponseDTO>> changeStatus(
        @PathVariable Long orderId,
        @Valid @RequestBody StatusRequestDTO status) {
      
        LogisticResponseDTO logistic = logisticService.changeStatus(orderId, status);

        return ResponseEntity.ok(
            new ApiResponse<>(200, "Logistica actualizada", logistic));
    }
}
