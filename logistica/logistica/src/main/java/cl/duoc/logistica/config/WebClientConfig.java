package cl.duoc.logistica.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient orderWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://order/api/v1/orders").build();
    }

    @Bean
    public WebClient userWebClient(WebClient.Builder builder) { 
        return builder.baseUrl("http://users/api/v1/users").build();
    }

    @Bean
    public WebClient notificationWebClient(WebClient.Builder builder){
        return builder.baseUrl("http://notifications/api/v1/notifications").build();
    }

    @Bean
    public WebClient paymentWebClient(WebClient.Builder builder){
        return builder.baseUrl("http://payment/api/v1/payment").build();
    }
}