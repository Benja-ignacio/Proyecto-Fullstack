package cl.duoc.logistica.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserClient {

    private final WebClient webClient;

    // Inyección explícita del Bean de usuarios
    public UserClient(@Qualifier("userWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Boolean existsByUserId(Long userId) {
        try {
            return webClient.get()
                .uri("/{userId}/exists", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        } catch (Exception e) {
            System.err.println("Error al conectar con USER-SERVICE: " + e.getMessage());
            return false;
        }
    }
}