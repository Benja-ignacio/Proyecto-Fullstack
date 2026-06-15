package cl.duoc.logistica.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LogisticClient {
    private final WebClient pedidoWebClient;

    public Boolean existsByOrderId(Long orderId){
        return pedidoWebClient.get()
            .uri("/exists/{orderId}", orderId)
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();
    }
}
