package cl.duoc.logistica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.duoc.logistica.security.JwtAuthenticationFilter; // El filtro que debes tener en logística
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Deshabilitado por usar JWT
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin estado
            )
            .authorizeHttpRequests(auth -> auth
                // Ejemplo de reglas para Logística:
                // 1. Ver despachos, rutas o camiones lo puede hacer ADMIN o EMPLOYEE
                .requestMatchers(HttpMethod.GET, "/api/v1/logistica/**").hasAnyRole("ADMIN", "EMPLOYEE")
                
                // 2. Acciones críticas como despachar, crear rutas o asignar choferes (POST, PUT, DELETE) 
                //    podrías limitarlas solo al ADMIN o a un rol específico si existe (ej: COORDINATOR)
                .requestMatchers("/api/v1/logistica/despachar/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/logistica/rutas/**").hasRole("ADMIN")
                
                // 3. Cualquier otra petición interna o endpoint de logística requiere autenticación
                .anyRequest().authenticated()
            )
            // Enganchamos el filtro para interceptar el token antes de evaluar los accesos
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}