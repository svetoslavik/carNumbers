package com.example.demo3.controller;

public class Response<T> {


    private boolean isSuccessful;
    private String error;
    T data;

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isIsSuccessful() {
        return isSuccessful;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }


    public Response(boolean isSuccessful, T data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public Response(String error) {
        this.isSuccessful = false;
        this.error = error;
    }
}
