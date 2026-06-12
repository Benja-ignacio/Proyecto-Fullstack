package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient.Builder webClientBuilder;

    public String createPayment(Long orderId) {

        return webClientBuilder.build()
                .post()
                .uri("http://payment/api/v1/payments/create?orderId=" + orderId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}