package com.luv2code.cruddemo.exception.custom;
import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends RuntimeException {
    private final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

}
