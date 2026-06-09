package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient.Builder webClientBuilder;

    public String createPayment(Long orderId) {
        return webClientBuilder.build()
                .post()
                .uri("http://PAYMENT/api/payments/create?orderId=" + orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
