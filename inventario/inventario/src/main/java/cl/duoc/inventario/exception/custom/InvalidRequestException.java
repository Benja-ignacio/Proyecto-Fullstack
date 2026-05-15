package cl.duoc.inventario.exception.custom;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }

}
