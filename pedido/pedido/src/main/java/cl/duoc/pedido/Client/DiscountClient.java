package cl.duoc.pedido.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class DiscountClient {

    private final WebClient.Builder webClientBuilder;

    public String getDiscount(Long userId) {
        return webClientBuilder.build()
                .get()
                .uri("http://DISCOUNT/api/discounts/user/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}