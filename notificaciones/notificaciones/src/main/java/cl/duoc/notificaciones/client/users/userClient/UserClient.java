package cl.duoc.notificaciones.client.users.userClient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserClient {
    private final WebClient webClient;

    public Boolean existsById(Long userId) {
        return webClient.get()
        .uri("/exists/{userId}", userId)
        .retrieve()
        .bodyToMono(Boolean.class)
        .block();
    }
}
