package cl.duoc.productos.exception.custom;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

}
