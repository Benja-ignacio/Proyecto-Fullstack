package cl.duoc.carrito.exception;

public class CartResourceNotFoundException extends RuntimeException {

    public CartResourceNotFoundException(String message) {
        super(message);
    }
}