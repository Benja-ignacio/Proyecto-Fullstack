<<<<<<< HEAD
=======
// package cl.duoc.usuarios.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import cl.duoc.usuarios.security.JwtAuthFilter;
// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig {

//     private final JwtAuthFilter jwtAuthFilter;

//     @Bean
//     @Order(1)
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Elimina el JSESSIONID
//             )
//             .authorizeHttpRequests(auth -> auth
//                 // rutas publicas
//                 .requestMatchers(
//                     "/api/v1/auth/login", 
//                     "/api/v1/auth/register",
//                     "/actuator/**",
//                     "/v3/api-docs/**",
//                     "/swagger-ui/**",
//                     "/swagger-ui.html",
//                     "/swagger-ui/index.html",
//                     "/webjars/**").permitAll()

//                 // rutas solo admin
//                 .requestMatchers(HttpMethod.PATCH, "/api/v1/users/status/**").hasRole("ADMIN")
//                 .requestMatchers("/api/v1/users/list").hasRole("ADMIN")
//                 .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasRole("ADMIN")
//                 .requestMatchers(HttpMethod.GET, "/api/v1/users/exists/{userId}").hasRole("ADMIN")
//                 // todo lo demas requiere autenticacion 
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//             .build();
//     }

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }


>>>>>>> eliascarcamo
package cl.duoc.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
>>>>>>> eliascarcamo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

<<<<<<< HEAD
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

=======
>>>>>>> eliascarcamo
@Configuration
public class SecurityConfig {

    @Bean
<<<<<<< HEAD
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
=======
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.duoc.usuarios.security.JwtAuthFilter;
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
                    "/api/v1/auth/login", 
                    "/api/v1/auth/register",
                    "/actuator/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-ui/index.html",
                    "/webjars/**").permitAll()

                // rutas solo admin
                .requestMatchers(HttpMethod.PATCH, "/api/v1/users/status/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/users/list").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/exists/{userId}").hasRole("ADMIN")
                // todo lo demas requiere autenticacion 
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
>>>>>>> benja
        return new BCryptPasswordEncoder();
    }
}
=======
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/api/v1/auth/**",
                            "/api/v1/users/validate",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/api-docs/**",
                            "/actuator/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}
>>>>>>> eliascarcamo
