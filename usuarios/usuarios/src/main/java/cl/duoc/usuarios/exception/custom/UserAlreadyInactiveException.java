package cl.duoc.usuarios.exception.custom;

public class UserAlreadyInactiveException extends RuntimeException{ 
    public UserAlreadyInactiveException(String message) {
        super(message);
    }

}
