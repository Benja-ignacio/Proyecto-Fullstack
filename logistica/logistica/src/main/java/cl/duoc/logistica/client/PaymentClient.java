package cl.duoc.logistica.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaymentClient {

    private final WebClient paymentWebClient;

    // Inyección explícita del Bean de pagos
    public PaymentClient(@Qualifier("paymentWebClient") WebClient paymentWebClient) {
        this.paymentWebClient = paymentWebClient;
    }

    public Boolean isOrderPaid(Long orderId) {
        try {
            return paymentWebClient.get()
                .uri("/order/{orderId}/is-paid", orderId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        } catch (Exception e) {
            System.err.println("Error al conectar con PAYMENT-SERVICE: " + e.getMessage());
            return false;
        }
    }
}