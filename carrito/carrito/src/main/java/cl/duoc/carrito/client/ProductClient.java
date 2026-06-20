package cl.duoc.carrito.client;

import cl.duoc.carrito.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient.Builder webClientBuilder;

    public ProductResponseDTO getProduct(Long productId){

        return webClientBuilder.build()
                .get()
                .uri("http://products-service/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductResponseDTO.class)
                .block();
    }

}