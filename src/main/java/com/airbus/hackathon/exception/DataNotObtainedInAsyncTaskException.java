package com.airbus.hackathon.exception;

public class DataNotObtainedInAsyncTaskException extends RuntimeException {

    private static final long serialVersionUID = 12L;

    public DataNotObtainedInAsyncTaskException(String message) {
        super(message);
    }
}
