package cl.duoc.carrito.client;

import cl.duoc.carrito.dto.InventoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class InventoryClient {

    private final WebClient.Builder webClientBuilder;


    public InventoryResponseDTO getStock(Long productId){

        return webClientBuilder.build()
                .get()
                .uri("http://inventory-service/api/v1/inventory/" + productId)
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class)
                .block();

    }

}