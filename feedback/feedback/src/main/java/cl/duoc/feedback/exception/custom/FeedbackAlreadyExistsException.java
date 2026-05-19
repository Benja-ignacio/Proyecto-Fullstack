package cl.duoc.feedback.exception.custom;

public class FeedbackAlreadyExistsException extends RuntimeException{

    public FeedbackAlreadyExistsException(String message) {
        super(message);
    }

}
