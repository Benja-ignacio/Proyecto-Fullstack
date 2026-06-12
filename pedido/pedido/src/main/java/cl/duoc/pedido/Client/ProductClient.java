package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient.Builder webClientBuilder;

    public String getProduct(Long productId) {

        return webClientBuilder.build()
                .get()
                .uri("http://productos/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}