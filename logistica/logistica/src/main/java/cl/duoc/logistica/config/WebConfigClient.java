package cl.duoc.logistica.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfigClient {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient orderWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://ORDER/api/v1/orders").build();
    }

    @Bean
    public WebClient userWebClient(WebClient.Builder builder) { 
        return builder.baseUrl("http://USER/api/v1/users").build();
    }
}