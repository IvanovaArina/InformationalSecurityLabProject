package exeption;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {

        super(message);
    }
    //TODO exceptions
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
