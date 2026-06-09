package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class LogisticClient {

    private final WebClient.Builder webClientBuilder;

    public String calculateShipping(Long orderId) {
        return webClientBuilder.build()
                .get()
                .uri("http://LOGISTIC/api/logistic/shipping/" + orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
