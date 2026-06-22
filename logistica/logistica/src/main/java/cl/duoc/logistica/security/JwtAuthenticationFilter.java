package cl.duoc.logistica.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // extraer la cabecera Authorization de la peticion
        String authHeader = request.getHeader("Authorization");

        // verificar que venga el formato Bearer <token>
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // corrtar la palabra "Bearer "

            // validar el token usando tu clase JwtUtil
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);

                // autenticar la petición en el contexto de Spring
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // conntinuar con el flujo normal de la petición
        filterChain.doFilter(request, response);
    }
}