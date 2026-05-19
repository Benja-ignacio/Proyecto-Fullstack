package cl.duoc.feedback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.duoc.feedback.Service.FeedbackService;
import cl.duoc.feedback.dto.ApiResponse;
import cl.duoc.feedback.dto.FeedbackRequestDTO;
import cl.duoc.feedback.dto.FeedbackResponseDTO;
import cl.duoc.feedback.dto.UpdateFeedbackRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    // crear feedback
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FeedbackResponseDTO>> create(
           @Valid @RequestBody FeedbackRequestDTO request) {

        FeedbackResponseDTO feedback = feedbackService.createFeedback(request);

        ApiResponse<FeedbackResponseDTO> response = new ApiResponse<FeedbackResponseDTO>(
                                                201, "Feedback creado", feedback);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // buscar producto por productId
    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse<List<FeedbackResponseDTO>>> getByProduct(
            @PathVariable Long productId) {

        List<FeedbackResponseDTO> data = feedbackService.getByProduct(productId);

        ApiResponse<List<FeedbackResponseDTO>> response = new ApiResponse<List<FeedbackResponseDTO>>(
                                                200, "Lista de feedbacks", data);
        
        return ResponseEntity.ok(response);
    }

    // buscar feedbacks de un usuario
    @GetMapping("user/{userId}")
    public ResponseEntity<ApiResponse<List<FeedbackResponseDTO>>> getByUser(
            @PathVariable Long userId) {

        List<FeedbackResponseDTO> data = feedbackService.getByUser(userId);

        ApiResponse<List<FeedbackResponseDTO>> response = new ApiResponse<List<FeedbackResponseDTO>>(
                                                200, "lista de feedbacks", data);

        return ResponseEntity.ok(response);
    }

    // buscar feedback por id 
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FeedbackResponseDTO>> getById(
        @PathVariable Long id) {
        
        FeedbackResponseDTO feedback = feedbackService.getById(id);

        ApiResponse<FeedbackResponseDTO> response = new ApiResponse<FeedbackResponseDTO>(
                                                200,"consulta exitosa", feedback);
        
        return ResponseEntity.ok(response);
    }
    
    // eliminar feedback
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        feedbackService.delete(id);
        
        ApiResponse<Void> response = new ApiResponse<Void>(
                                200,"Feedback eliminado", null);

        return ResponseEntity.ok(response);
    }

    // actualizar feedback
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<FeedbackResponseDTO>> update(
        @PathVariable Long id, @Valid @RequestBody UpdateFeedbackRequestDTO request) {
        
        FeedbackResponseDTO data = feedbackService.update(id, request);

        ApiResponse<FeedbackResponseDTO> response = new ApiResponse<FeedbackResponseDTO>(200, "Feedback modificado", data);

        return ResponseEntity.ok(response);
    }
}       