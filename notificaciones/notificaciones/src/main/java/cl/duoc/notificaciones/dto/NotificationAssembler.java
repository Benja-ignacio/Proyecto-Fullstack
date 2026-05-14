package cl.duoc.notificaciones.dto;

import cl.duoc.notificaciones.model.Notification;

public class NotificationAssembler {

    // Convierte NotificationDTO -> Notification
    public static Notification toEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setUserId(dto.getUserId());
        notification.setTitle(dto.getTitle());
        notification.setMessage(dto.getMessage());
        return notification;
    }

    // Convierte Notification -> NotificationDTO
    public static NotificationDTO toDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setUserId(notification.getUserId());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        return dto;
    }
}