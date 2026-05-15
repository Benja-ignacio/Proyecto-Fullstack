package cl.duoc.descuentos.exception.custom;

public class DiscountAlreadyActiveException extends RuntimeException{
    public DiscountAlreadyActiveException(String message) {
        super(message);
    }
}
