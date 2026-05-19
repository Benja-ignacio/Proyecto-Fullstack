package cl.duoc.notificaciones.exception.custom;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message) {
        super(message);
    }

}
