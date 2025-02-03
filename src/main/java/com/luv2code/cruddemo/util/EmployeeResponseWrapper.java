package com.luv2code.cruddemo.util;

import com.luv2code.cruddemo.exception.model.EmployeeExceptionResponse;
import com.luv2code.cruddemo.model.response.EmployeeSuccessResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class EmployeeResponseWrapper {
    public static <T> ResponseEntity<EmployeeSuccessResponse<T>> buildSuccessResponse(HttpStatusCode httpStatusCode, HttpMethod httpMethod, T data) {
        EmployeeSuccessResponse<T> response = new EmployeeSuccessResponse<>(httpStatusCode.value(), httpMethod, data);
        return new ResponseEntity<>(response, httpStatusCode);
    }

    public static <T> ResponseEntity<EmployeeExceptionResponse> buildErrorResponse(String message, HttpStatusCode httpStatusCode, long timestamp) {
        EmployeeExceptionResponse employeeExceptionResponse = new EmployeeExceptionResponse(message, httpStatusCode.value(), timestamp);
        return new ResponseEntity<EmployeeExceptionResponse>(employeeExceptionResponse, httpStatusCode);
    }
}
