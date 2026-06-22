package cl.duoc.carrito.client;

import cl.duoc.carrito.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public UserResponseDTO getUser(Long userId){

        return webClientBuilder.build()
                .get()
                .uri("http://users-service/api/users/" + userId)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }
}