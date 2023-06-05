package day05javaswingrestclient;

public class ApiErrorException extends Exception { // checked exceptions
    
     public ApiErrorException() {
    }

    public ApiErrorException(String message) {
        super(message);
    }

    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorException(Throwable cause) {
        super(cause);
    }
    
}
