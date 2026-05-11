package cl.duoc.pedido.exception;

public class OrderResourceNotFoundException extends RuntimeException {

    public OrderResourceNotFoundException(String message) {
        super(message);
    }
}