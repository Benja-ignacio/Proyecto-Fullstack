package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DiscountClient {

    private final WebClient.Builder webClientBuilder;

    public String getDiscount(Long userId) {

        return webClientBuilder.build()
                .get()
                .uri("http://discounts/api/v1/discounts/user/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}