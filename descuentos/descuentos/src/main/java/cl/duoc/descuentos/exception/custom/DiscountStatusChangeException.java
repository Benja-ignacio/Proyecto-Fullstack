package cl.duoc.descuentos.exception.custom;

public class DiscountStatusChangeException extends RuntimeException{
    public DiscountStatusChangeException(String message) {
        super(message);
    }
}
