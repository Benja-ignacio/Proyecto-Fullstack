package cl.duoc.pedido.Client;

import cl.duoc.pedido.dto.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartClient {

    private final WebClient.Builder webClientBuilder;

    public List<CartItemResponse> getCartItems(Long userId){

        return webClientBuilder.build()
                .get()
                .uri("http://cart/api/cart?userId=" + userId)
                .retrieve()
                .bodyToMono(
                    new ParameterizedTypeReference<List<CartItemResponse>>() {}
                )
                .block();
    }
}