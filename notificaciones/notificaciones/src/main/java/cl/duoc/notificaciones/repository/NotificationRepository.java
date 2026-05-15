package cl.duoc.notificaciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.notificaciones.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Buscar todas las notificaciones de un usuario
    List<Notification> findByUserId(Long userId);

    // Buscar notificaciones de un usuario filtrando por leída/no leída
    List<Notification> findByUserIdAndRead(Long userId, Boolean read);

    // buscar notificacion por id 
    Optional<Notification> findById(Long id);
}