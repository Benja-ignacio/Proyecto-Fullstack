package cl.duoc.descuentos.exception.custom;

public class DiscountNotFoundException extends RuntimeException{

    public DiscountNotFoundException(String message) {
        super(message);
    }
}
