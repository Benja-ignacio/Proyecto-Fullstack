package cl.duoc.usuarios.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.duoc.usuarios.exception.custom.EmailAlreadyUsedException;
import cl.duoc.usuarios.exception.custom.UserAlreadyExistsException;
import cl.duoc.usuarios.exception.custom.UserAlreadyInStatusException;
import cl.duoc.usuarios.exception.custom.UserAlreadyInactiveException;
import cl.duoc.usuarios.exception.custom.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
        private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

            
    @ExceptionHandler(Exception.class) // EXCEPCIONES GLOBALES 
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {

        // log interno completo
        logger.error("Error inesperado", ex);

        // respuesta limpia al cliente
        Map<String, String> response = Map.of(
                "error", "Error interno del servidor"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // ERRORES DE VALID
    public ResponseEntity<Map<String, String>> validExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        logger.warn("Error de validación: {}", ex.getMessage());
        
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }


    // NOTA usar ex en ves de ex.getMessage cuando se necesite ver los logs completos. 

    @ExceptionHandler(UserAlreadyExistsException.class) 
    public ResponseEntity<Map<String, String>> userAlreadyExists(UserAlreadyExistsException ex) {

        logger.warn("El usuario ya existe: {}", ex.getMessage()); // LOGS QUE SOLO SE PUEDE VER INTERNAMENTE

        Map<String, String> error = Map.of(
            "error", ex.getMessage() // ERROR QUE VE EL FRONTEND
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<Map<String, String>> emailAlreadyUsed(EmailAlreadyUsedException ex) {

        logger.warn("El email ya esta registrado: {}", ex.getMessage()); // LOGS QUE SOLO SE PUEDE VER INTERNAMENTE

        Map<String, String> error = Map.of(
            "error", ex.getMessage() // ERROR QUE VE EL FRONTEND
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserAlreadyInStatusException.class)
    public ResponseEntity<Map<String, String>> userAlreadyInStatus(UserAlreadyInStatusException ex) {

        logger.warn("El usuario ya se encuntra con el estado de cuenta ingresado: {}", ex.getMessage()); // LOGS QUE SOLO SE PUEDE VER INTERNAMENTE

        Map<String, String> error = Map.of(
            "error", ex.getMessage() // ERROR QUE VE EL FRONTEND
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFound(UserNotFoundException ex) {

        logger.warn("El usuario no se encontro: {}", ex.getMessage()); // LOGS QUE SOLO SE PUEDE VER INTERNAMENTE

        Map<String, String> error = Map.of(
            "error", ex.getMessage() // ERROR QUE VE EL FRONTEND
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserAlreadyInactiveException.class)
    public ResponseEntity<Map<String, String>> userAlreadyInactive(UserAlreadyInactiveException ex) {

        logger.warn("El usuario ya se encuentra con la cuenta inactiva: {}", ex.getMessage()); // LOGS QUE SOLO SE PUEDE VER INTERNAMENTE

        Map<String, String> error = Map.of(
            "error", ex.getMessage() // ERROR QUE VE EL FRONTEND
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    
}
