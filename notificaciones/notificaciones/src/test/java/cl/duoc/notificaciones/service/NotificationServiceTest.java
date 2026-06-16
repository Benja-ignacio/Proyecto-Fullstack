package cl.duoc.notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.notificaciones.client.users.userClient.UserClient;
import cl.duoc.notificaciones.dto.NotificationResponseDTO;
import cl.duoc.notificaciones.enums.Type;
import cl.duoc.notificaciones.mapper.NotificationsMapper;
import cl.duoc.notificaciones.model.Notification;
import cl.duoc.notificaciones.repository.NotificationRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class NotificationServiceTest {
    @Test
        void testGetAllNotifications() {
        NotificationRepository notificationRepository = Mockito.mock(NotificationRepository.class);
        NotificationsMapper mapper = Mockito.mock(NotificationsMapper.class); // ← faltaba este
        UserClient userClient = Mockito.mock(UserClient.class);


        NotificationService notificationService = new NotificationService(notificationRepository, userClient, mapper);

        Notification notification = new Notification(1L, 1l, "Nuevo producto", "Se agrego un nuevo producto", Type.NEW_PRODUCT, LocalDateTime.now(), false);
        Mockito.when(notificationRepository.findAll()).thenReturn(List.of(notification));

        List<NotificationResponseDTO> result = notificationService.findAll();

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
    }
}
