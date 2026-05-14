package cl.duoc.notificaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.notificaciones.model.Notification;
import cl.duoc.notificaciones.service.notificationService;

@RestController
@RequestMapping("/api/notifications")
public class notificationController {

    @Autowired
    private notificationService notificationService;

    // Obtener todas las notificaciones
    @GetMapping
    public List<Notification> getAll() {
        return notificationService.findAll();
    }

    // Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.findById(id);

        if (notification.isPresent()) {
            return ResponseEntity.ok(notification.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Obtener notificaciones por usuario
    @GetMapping("/user/{userId}")
    public List<Notification> getByUserId(@PathVariable Long userId) {
        return notificationService.findByUserId(userId);
    }

    // Crear una nueva notificación
    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

    // Marcar como leída
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        Notification notification = notificationService.markAsRead(id);

        if (notification != null) {
            return ResponseEntity.ok(notification);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar una notificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}