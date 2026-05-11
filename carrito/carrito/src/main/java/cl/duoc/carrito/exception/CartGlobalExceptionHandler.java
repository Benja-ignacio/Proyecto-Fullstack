package cl.duoc.carrito.exception;

import cl.duoc.carrito.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartGlobalExceptionHandler {

    @ExceptionHandler(CartResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(
            CartResourceNotFoundException ex) {

        return new ResponseEntity<>(
                new ApiResponse<>(
                        404,
                        ex.getMessage(),
                        null
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return new ResponseEntity<>(
                new ApiResponse<>(
                        400,
                        errorMessage,
                        null
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneral(
            Exception ex) {

        return new ResponseEntity<>(
                new ApiResponse<>(
                        500,
                        "Error interno del servidor",
                        null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}