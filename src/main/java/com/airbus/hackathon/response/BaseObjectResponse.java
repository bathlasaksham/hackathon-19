package com.airbus.hackathon.response;

public class BaseObjectResponse<T> extends AbstractResponse{

    private T data;

    public BaseObjectResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public BaseObjectResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
