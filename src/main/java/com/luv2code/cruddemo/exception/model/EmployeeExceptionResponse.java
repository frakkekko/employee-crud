package com.luv2code.cruddemo.exception.model;

public class EmployeeExceptionResponse {
    private long timeStamp;
    private String message;
    private int httpStatusCode;

    public EmployeeExceptionResponse(){}

    public EmployeeExceptionResponse(String message, int httpStatusCode){
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public EmployeeExceptionResponse(String message, int httpStatusCode, long timeStamp){
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.timeStamp = timeStamp;
    }


    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
