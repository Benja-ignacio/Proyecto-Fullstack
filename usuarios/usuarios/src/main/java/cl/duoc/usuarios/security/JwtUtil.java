package cl.duoc.usuarios.security;

import java.security.Key;
import java.util.Date;


import org.springframework.stereotype.Component;

import cl.duoc.usuarios.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
    // mayusculas, minusculas, numeros y al menos 32 caracteres
    private final String SECRET_KEY = "fdspijgiurelkjfewvdsAIJD45SAPIJFDSAPIFSAIE93298432";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2  horas

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    // generar token 
    public String generateToken(Long userId, String username, Role role) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("username",username)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    // validar token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Role extractRole(String token) {
        String roleString = extractAllClaims(token).get("role", String.class);
        return Role.valueOf(roleString);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }


}
