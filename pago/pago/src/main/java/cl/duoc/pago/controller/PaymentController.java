package cl.duoc.pago.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pago.dto.ApiResponse;
import cl.duoc.pago.dto.PaymentConfirmRequestDTO;
import cl.duoc.pago.dto.PaymentInitRequestDTO;
import cl.duoc.pago.dto.PaymentResponseDTO;
import cl.duoc.pago.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    // buscar por id
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> getById(
        @PathVariable Long id) {

        PaymentResponseDTO data = paymentService.getById(id);

        ApiResponse<PaymentResponseDTO> response = new ApiResponse<>(200, "Pago encontrado", data);

        return ResponseEntity.ok(response);
    }

    // buscar por orderId

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> getByOrderId(
        @PathVariable Long orderId) {

        PaymentResponseDTO data = paymentService.getByOrderId(orderId);

        ApiResponse<PaymentResponseDTO> response = new ApiResponse<>(200, "Pago encontrado", data);

        return ResponseEntity.ok(response);
    }
    
    // iniciar pago
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> initPayment(
        @Valid @RequestBody PaymentInitRequestDTO dto) {

        PaymentResponseDTO data = paymentService.initPayment(dto);

        ApiResponse<PaymentResponseDTO> response = new ApiResponse<>(201, "pago iniciado", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // confirmar pago
    @PostMapping("/confirm")
    public ResponseEntity<ApiResponse<PaymentResponseDTO>> confirmPayment(
        @Valid @RequestBody PaymentConfirmRequestDTO dto) {
        
        PaymentResponseDTO data = paymentService.confirmPayment(dto);

        ApiResponse<PaymentResponseDTO> response = new ApiResponse<>(200, "Pago procesado", data);

        return ResponseEntity.ok(response);
    }
    

    // eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.cancelPayment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
