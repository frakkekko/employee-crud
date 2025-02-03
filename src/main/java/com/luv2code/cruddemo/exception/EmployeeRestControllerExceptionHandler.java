package com.luv2code.cruddemo.exception;

import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.exception.model.EmployeeExceptionResponse;
import com.luv2code.cruddemo.util.EmployeeResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(EmployeeNotFoundException exception){
        return EmployeeResponseWrapper.buildErrorResponse(exception.getMessage(), exception.getStatusCode(), System.currentTimeMillis());
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(HttpRequestMethodNotSupportedException exception){
        return EmployeeResponseWrapper.buildErrorResponse(exception.getMessage(), exception.getStatusCode(), System.currentTimeMillis());
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(MethodArgumentNotValidException exception){
        return EmployeeResponseWrapper.buildErrorResponse("Invalid body arguments", exception.getStatusCode(), System.currentTimeMillis());
    }


}
