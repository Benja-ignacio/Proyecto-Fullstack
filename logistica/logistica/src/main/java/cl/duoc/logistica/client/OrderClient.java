package cl.duoc.logistica.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderClient {

    private final WebClient webClient;

    // Constructor manual para evitar el conflicto de múltiples Beans en Docker
    public OrderClient(@Qualifier("orderWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Boolean existsByOrderId(Long orderId) {
        try {
            return webClient.get()
                .uri("/{orderId}/exists", orderId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        } catch (Exception e) {
            System.err.println("Error al conectar con ORDER-SERVICE: " + e.getMessage());
            return false;
        }
    }
}