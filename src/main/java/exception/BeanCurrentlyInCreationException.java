package exception;

public class BeanCurrentlyInCreationException extends RuntimeException {
    public BeanCurrentlyInCreationException() {
    }

    public BeanCurrentlyInCreationException(String message) {
        super(message);
    }
}
