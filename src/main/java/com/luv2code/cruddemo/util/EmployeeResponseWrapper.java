package com.luv2code.cruddemo.util;

import com.luv2code.cruddemo.model.response.EmployeeSuccessResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class EmployeeResponseWrapper {
    public static <T> HttpEntity<EmployeeSuccessResponse<T>> buildResponse(int httpStatusValue, HttpMethod httpMethod, T data) {
        EmployeeSuccessResponse<T> response = new EmployeeSuccessResponse<>(httpStatusValue, httpMethod, data);
        return new HttpEntity<>(response);
    }
}
