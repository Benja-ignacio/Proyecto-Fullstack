package cl.duoc.pedido.Client;

import cl.duoc.pedido.dto.ApiResponse;
import cl.duoc.pedido.dto.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartClient {

    private final WebClient.Builder webClientBuilder;

    public List<CartItemResponse> getCartItems(Long userId) {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String authorizationHeader = null;

        if (attributes != null) {
            authorizationHeader = attributes.getRequest().getHeader("Authorization");
        }

        String finalAuthorizationHeader = authorizationHeader;

        ApiResponse<List<CartItemResponse>> response = webClientBuilder.build()
                .get()
                .uri("http://cart/api/cart?userId=" + userId)
                .headers(headers -> {
                    if (finalAuthorizationHeader != null) {
                        headers.set("Authorization", finalAuthorizationHeader);
                    }
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<CartItemResponse>>>() {})
                .block();

        if (response == null || response.getData() == null) {
            return List.of();
        }

        return response.getData();
    }
}