package cl.duoc.inventario.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.inventario.exception.custom.InvalidRequestException;

@Component("productWebClientComponent")
public class ProductWebClient {

    private final WebClient webClient;

    public ProductWebClient(@Qualifier("productWebClient") WebClient webClient){
        this.webClient = webClient;
    }

    public void productExists(Long productId){
        try{
            webClient.get()
                    .uri("/" + productId)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e){
            throw new InvalidRequestException("El producto con id " + productId + " no existe");
        }
    }
}
