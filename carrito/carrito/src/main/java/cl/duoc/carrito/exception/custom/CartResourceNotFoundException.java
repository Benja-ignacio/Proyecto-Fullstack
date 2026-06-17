package cl.duoc.carrito.exception.custom;

public class CartResourceNotFoundException extends RuntimeException {

    public CartResourceNotFoundException(String message) {
        super(message);
    }
}