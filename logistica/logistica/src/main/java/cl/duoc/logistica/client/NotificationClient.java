package cl.duoc.logistica.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class NotificationClient {

    private final WebClient notificationWebClient;

    // Inyección explícita del Bean de notificaciones
    public NotificationClient(@Qualifier("notificationWebClient") WebClient notificationWebClient) {
        this.notificationWebClient = notificationWebClient;
    }

    public void sendStatusUpdateNotification(Long userId, Long orderId, String status) {
        Map<String, Object> requestBody = Map.of(
            "userId", userId,
            "orderId", orderId,
            "message", "El estado de tu envío para el pedido #" + orderId + " ha cambiado a: " + status
        );

        notificationWebClient.post()
            .uri("/send")
            .bodyValue(requestBody)
            .retrieve()
            .toBodilessEntity()
            .subscribe(
                success -> System.out.println("Notificación enviada con éxito al servicio"),
                error -> System.err.println("Error asíncrono en NOTIFICATION-SERVICE: " + error.getMessage())
            );
    }
}