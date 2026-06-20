// package cl.duoc.usuarios.security;

// import java.security.Key;
// import java.util.Date;

// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtUtil {
//     // mayusculas, minusculas, numeros y al menos 32 caracteres
//     private final String SECRET_KEY = "fdspijgiurelkjfewvdsAIJD45SAPIJFDSAPIFSAIE93298432";
//     private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2  horas

//     private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


//     // generar token 
//     public String generateToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }


//     // validar token
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         } catch (JwtException e) {
//             return false;
//         }
//     }

//     // extraer username 
//     public String extractUsername(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }


// }



//prueba rama eliascarcamo
package cl.duoc.usuarios.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "fdspijgiurelkjfewvdsAIJD45SAPIJFDSAPIFSAIE93298432";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2 horas

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generar token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("username", username)
                .claim("role", "CLIENT")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Extraer todos los datos del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraer username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extraer rol
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }
}