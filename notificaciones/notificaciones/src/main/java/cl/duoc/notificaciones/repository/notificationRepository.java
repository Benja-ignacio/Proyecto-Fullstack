package cl.duoc.notificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.notificaciones.model.Notification;

@Repository
public interface notificationRepository extends JpaRepository<Notification, Long> {

    // Buscar todas las notificaciones de un usuario
    List<Notification> findByUserId(Long userId);

    // Buscar notificaciones de un usuario filtrando por leída/no leída
    List<Notification> findByUserIdAndRead(Long userId, Boolean read);
}