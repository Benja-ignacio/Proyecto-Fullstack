package cl.duoc.feedback.exception.custom;

public class FeedbackNotFoundException extends RuntimeException{

    public FeedbackNotFoundException(String message) {
        super(message);
    }
}
