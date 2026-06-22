package cl.duoc.productos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.duoc.productos.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;


    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Elimina el JSESSIONID
            )
            .authorizeHttpRequests(auth -> auth
                // rutas publicas
                .requestMatchers(
        "/api/v1/products/*",
                    "/v3/api-docs/**",
                    "/actuator/**",
                    "/swagger-ui/**",
                    "/swagger-ui/index.html",
                    "/webjars/**").permitAll()

                // rutas admin
                .requestMatchers(HttpMethod.POST, "/api/v1/products/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/products/update/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/products/update/status/*")    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/delete/**").hasRole("ADMIN")
                // todo lo demas autenticado
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
            
    }
}

