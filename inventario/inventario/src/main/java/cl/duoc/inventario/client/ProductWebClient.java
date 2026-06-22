package cl.duoc.inventario.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.inventario.exception.custom.InvalidRequestException;

<<<<<<< HEAD
@Component("productWebClientComponent")
=======
@Component
>>>>>>> eliascarcamo
public class ProductWebClient {

    private final WebClient webClient;

<<<<<<< HEAD
    public ProductWebClient(@Qualifier("productWebClient") WebClient webClient){
=======
    public ProductWebClient (@Qualifier("ProductWebClient") WebClient webClient){
>>>>>>> eliascarcamo
        this.webClient = webClient;
    }

    public void productExists(Long productId){
        try{
<<<<<<< HEAD
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
=======
        webClient.get()
                .uri("/" +  productId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        } catch (Exception e){
            throw new InvalidRequestException("El producto con id"+ productId + "no existe");
        }
    }
}
>>>>>>> eliascarcamo
