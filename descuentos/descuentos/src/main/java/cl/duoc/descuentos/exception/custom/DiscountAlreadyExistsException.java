package cl.duoc.descuentos.exception.custom;

public class DiscountAlreadyExistsException extends RuntimeException{
    public DiscountAlreadyExistsException(String message) {
        super(message);
    }
}
