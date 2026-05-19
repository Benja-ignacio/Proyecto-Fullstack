package cl.duoc.pedido.exception.custom;

public class OrderResourceNotFoundException extends RuntimeException {

    public OrderResourceNotFoundException(String message) {
        super(message);
    }
}