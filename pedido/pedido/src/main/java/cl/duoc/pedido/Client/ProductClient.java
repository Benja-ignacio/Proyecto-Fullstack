package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient.Builder webClientBuilder;

    public String getProduct(Long productId) {
        return webClientBuilder.build()
                .get()
                .uri("http://PRODUCTOS/api/products/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
