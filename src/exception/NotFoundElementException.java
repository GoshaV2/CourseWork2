package exception;

public class NotFoundElementException extends Exception {
    public NotFoundElementException() {
    }

    public NotFoundElementException(String message) {
        super(message);
    }
}
