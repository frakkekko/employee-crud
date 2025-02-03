package com.luv2code.cruddemo.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luv2code.cruddemo.util.HttpMethodSerializer;
import org.springframework.http.HttpMethod;

public class EmployeeSuccessResponse<T> {
    private int httpStatus;

    @JsonSerialize(using = HttpMethodSerializer.class)
    private HttpMethod httpMethod;

    private  T data;

    public EmployeeSuccessResponse(int httpStatus, HttpMethod httpMethod, T data) {
        this.httpStatus = httpStatus;
        this.httpMethod = httpMethod;
        this.data = data;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
