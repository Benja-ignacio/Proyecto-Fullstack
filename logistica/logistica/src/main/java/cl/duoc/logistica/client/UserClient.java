package cl.duoc.logistica.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient userWebClient;

    public Boolean existsByUserId(Long userId) {
        return userWebClient.get()
                .uri("/exists/{userId}", userId) 
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // Lo hace síncrono para esperar la respuesta
    }
}