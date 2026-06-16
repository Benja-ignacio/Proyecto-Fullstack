package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LogisticClient {

    private final WebClient.Builder webClientBuilder;

    public String calculateShipping(Long orderId) {
        return webClientBuilder.build()
                .get()
                .uri("http://logistic/api/v1/logistic/shipping/" + orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
