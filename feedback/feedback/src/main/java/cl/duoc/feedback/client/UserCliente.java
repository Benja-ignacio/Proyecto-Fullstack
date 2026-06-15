package cl.duoc.feedback.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCliente {
    private final WebClient webClient;

    public boolean existsById(Long userId){
        return webClient.get()
        .uri("/exists/{userId}",userId)
        .retrieve()
        .bodyToMono(Boolean.class)
        .block();
    }

}
