package cl.duoc.usuarios.exception.custom;

public class UserAlreadyInStatusException extends RuntimeException{
    public UserAlreadyInStatusException(String message) {
        super(message);
    }

}
