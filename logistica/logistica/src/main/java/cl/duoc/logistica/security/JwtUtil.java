package cl.duoc.logistica.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // IMPORTANTE: Esta clave DEBE ser exactamente la misma que usa el servicio de Usuarios
    // para que Logística pueda validar correctamente los tokens que emite Usuarios.
    private final String SECRET_KEY = "fdspijgiurelkjfewvdsAIJD45SAPIJFDSAPIFSAIE93298432";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2 horas

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generar token (Útil por si Logística necesita firmar tokens internos)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token que llega desde las peticiones HTTP
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Extraer el username para saber qué usuario está operando en Logística
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}