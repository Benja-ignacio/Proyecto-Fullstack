package cl.duoc.notificaciones.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.notificaciones.dto.NotificationResponseDTO;
import cl.duoc.notificaciones.model.Notification;

@Component
public class NotificationsMapper {
    // entity a dto
    public NotificationResponseDTO entityToNotificationResponseDTO(Notification notification) {
        return NotificationResponseDTO.builder()
        .id(notification.getId())
        .userId(notification.getUserId())
        .title(notification.getTitle())
        .message(notification.getMessage())
        .type(notification.getType())
        .createdAt(notification.getCreatedAt())
        .read(notification.getRead())
        .build();
    }
}
