package cl.duoc.notificaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.notificaciones.dto.NotificationRequestDTO;
import cl.duoc.notificaciones.dto.NotificationResponseDTO;
import cl.duoc.notificaciones.exception.custom.NotificationNotFoundException;
import cl.duoc.notificaciones.model.Notification;
import cl.duoc.notificaciones.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


    // crear notification 
    public NotificationResponseDTO create (NotificationRequestDTO request) {
        Notification notification = new Notification();

        notification.setUserId(request.getUserId());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());

        notificationRepository.save(notification);

        return mapToDTO(notification);
    }



    // obtener todas las notificaciones
    public List<NotificationResponseDTO> findAll(){

        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
        .map(this::mapToDTO)
        .toList();
    }

    // buscar notificacion por id
    public NotificationResponseDTO findById(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notificacion no encontrada."));

        return mapToDTO(notification);
    }

    // buscar todas las notifaciones de un usuario
    public List<NotificationResponseDTO> findByUserId(Long userId){
        return notificationRepository.findByUserId(userId)
            .stream()
            .map(this::mapToDTO)
            .toList();
    }

    // marcar una notificacion como leida
    public NotificationResponseDTO markAsRead(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notificacion no encontrada"));

        notification.setRead(true);

        notificationRepository.save(notification);

        return mapToDTO(notification);
    }

    //eliminar notificacion
    public void deleteById(Long id){
        Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotificationNotFoundException("Notification no encontrada"));

        notificationRepository.delete(notification);
    }

    // entity a dto
    public NotificationResponseDTO mapToDTO(Notification notification) {
        return NotificationResponseDTO.builder()
        .id(notification.getId())
        .userId(notification.getUserId())
        .title(notification.getTitle())
        .message(notification.getMessage())
        .type(notification.getType())
        .createdAt(notification.getCreatedAt())
        .build();
    }
    

}
