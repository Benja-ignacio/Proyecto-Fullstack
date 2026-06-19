package cl.duoc.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.notificaciones.client.users.userClient.UserClient;
import cl.duoc.notificaciones.dto.NotificationRequestDTO;
import cl.duoc.notificaciones.dto.NotificationResponseDTO;
import cl.duoc.notificaciones.exception.client.user.UserNotFoundException;
import cl.duoc.notificaciones.exception.custom.NotificationNotFoundException;
import cl.duoc.notificaciones.mapper.NotificationsMapper;
import cl.duoc.notificaciones.model.Notification;
import cl.duoc.notificaciones.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserClient userClient;
    private final NotificationsMapper mapper;

    // admin
    // crear notification 
    public NotificationResponseDTO create (NotificationRequestDTO request) {
        boolean exists = userClient.existsById(request.getUserId());

        if (!exists) {
            throw new UserNotFoundException("El usuario con id: " + request.getUserId() + " no fue encontrado");
        }

        Notification notification = new Notification();

        notification.setUserId(request.getUserId());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());

        notificationRepository.save(notification);

        return mapper.entityToNotificationResponseDTO(notification);
    }


    // ADMIN
    // obtener todas las notificaciones
    public List<NotificationResponseDTO> findAll(){

        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
        .map(mapper::entityToNotificationResponseDTO)
        .toList();
    }

    // ADMIN
    // buscar notificacion por id
    public NotificationResponseDTO findById(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notificacion no encontrada."));

        return mapper.entityToNotificationResponseDTO(notification);
    }

    // ADMIN
    // buscar todas las notifaciones de un usuario
    public List<NotificationResponseDTO> findByUserId(Long userId){
        return notificationRepository.findByUserId(userId)
            .stream()
            .map(mapper::entityToNotificationResponseDTO)
            .toList();
    }

    // AUTH
    // marcar una notificacion como leida
    public NotificationResponseDTO markAsRead(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notificacion no encontrada"));

        notification.setRead(true);

        notificationRepository.save(notification);

        return mapper.entityToNotificationResponseDTO(notification);
    }

    // ADMIN
    //eliminar notificacion
    public void deleteById(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notification no encontrada"));

        notificationRepository.delete(notification);
    }

}
