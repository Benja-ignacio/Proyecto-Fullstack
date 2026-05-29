package cl.duoc.notificaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.notificaciones.dto.ApiResponse;
import cl.duoc.notificaciones.dto.NotificationRequestDTO;
import cl.duoc.notificaciones.dto.NotificationResponseDTO;
import cl.duoc.notificaciones.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {


    private final NotificationService notificationService;

    // Obtener todas las notificaciones
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationResponseDTO>>> getAll() {
        List<NotificationResponseDTO> list = notificationService.findAll();

        ApiResponse<List<NotificationResponseDTO>> response = new ApiResponse<List<NotificationResponseDTO>>(
                                                    200,"Lista de notificatciones", list);
        return ResponseEntity.ok(response);
    }

    // Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationResponseDTO>> getById(
        @PathVariable Long id) {

        NotificationResponseDTO data = notificationService.findById(id);

        ApiResponse<NotificationResponseDTO> response = new ApiResponse<NotificationResponseDTO>(
                                            200,"Consulta exitosa", data);
        
        return ResponseEntity.ok(response);
    }

    // Obtener notificaciones por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<NotificationResponseDTO>>> getByUserId(
        @PathVariable Long userId) {
        
            List<NotificationResponseDTO> data = notificationService.findByUserId(userId);

        ApiResponse<List<NotificationResponseDTO>> response = new ApiResponse<List<NotificationResponseDTO>>(
                                                    200, "Lista de notificaciones", data);
        return ResponseEntity.ok(response);
    }

    // Crear una nueva notificación
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<NotificationResponseDTO>> create(
        @Valid @RequestBody NotificationRequestDTO notification) {
        
        NotificationResponseDTO data = notificationService.create(notification);

        ApiResponse<NotificationResponseDTO> response = new ApiResponse<NotificationResponseDTO>(
                                                    201, "Notificacion creada", data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Marcar como leída
    @PutMapping("/read/{id}")
    public ResponseEntity<ApiResponse<NotificationResponseDTO>> markAsRead(
        @PathVariable Long id) {
        NotificationResponseDTO notification = notificationService.markAsRead(id);

        ApiResponse<NotificationResponseDTO> response = new ApiResponse<NotificationResponseDTO>(
                                                200,"Notificacion marcada como leida", notification);
        return ResponseEntity.ok(response);
    }

    // Eliminar una notificación
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        notificationService.deleteById(id);

        ApiResponse<Void> response = new ApiResponse<Void>(204,"Notificacion eliminada", null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}