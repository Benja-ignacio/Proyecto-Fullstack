package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CartClient {

    private final WebClient.Builder webClientBuilder;

    public String getCart(Long userId){

        return webClientBuilder.build()
                .get()
                .uri("http://cart/api/cart?userId=" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}