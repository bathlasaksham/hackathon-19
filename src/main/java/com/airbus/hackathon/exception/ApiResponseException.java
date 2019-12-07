package com.airbus.hackathon.exception;

public class ApiResponseException extends Exception {

    private static final long serialVersionUID = 7608453885970871524L;

    public ApiResponseException(String message) {
        super(message);
    }

}
