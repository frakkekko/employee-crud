package com.luv2code.cruddemo.exception;

import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.exception.model.EmployeeExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(EmployeeNotFoundException exception){
        EmployeeExceptionResponse employeeExceptionResponse = new EmployeeExceptionResponse(exception.getMessage(), exception.getStatusCode().value(), System.currentTimeMillis());
        return new ResponseEntity<EmployeeExceptionResponse>(employeeExceptionResponse, exception.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(HttpRequestMethodNotSupportedException exception){
        EmployeeExceptionResponse employeeExceptionResponse = new EmployeeExceptionResponse(exception.getMessage(), exception.getStatusCode().value(), System.currentTimeMillis());
        return new ResponseEntity<EmployeeExceptionResponse>(employeeExceptionResponse, exception.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> handleException(MethodArgumentNotValidException exception){
        EmployeeExceptionResponse employeeExceptionResponse = new EmployeeExceptionResponse("Invalid body arguments", exception.getStatusCode().value(), System.currentTimeMillis());
        return new ResponseEntity<EmployeeExceptionResponse>(employeeExceptionResponse, exception.getStatusCode());
    }


}
