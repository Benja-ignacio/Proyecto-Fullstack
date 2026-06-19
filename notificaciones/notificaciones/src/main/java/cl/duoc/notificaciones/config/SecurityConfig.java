package cl.duoc.notificaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.duoc.notificaciones.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpMethod;

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
                "/actuator/**",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/webjars/**").permitAll()

                // solo admin
                .requestMatchers( HttpMethod.GET, "/api/v1/notification/*").hasRole("ADMIN")
                .requestMatchers( HttpMethod.GET, "/api/v1/notification/user/*").hasRole("ADMIN")
                .requestMatchers( HttpMethod.POST, "/api/v1/notification/create").hasRole("ADMIN")
                .requestMatchers( HttpMethod.POST, "/api/v1/notification/delete/*").hasRole("ADMIN")
        
                // todo lo demas autenticado
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
