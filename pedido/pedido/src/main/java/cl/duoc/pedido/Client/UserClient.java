package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    public String getUser(Long userId){

        return webClientBuilder.build()
                .get()
                .uri("http://users/api/users/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}