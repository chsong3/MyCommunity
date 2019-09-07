package com.cs.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(CustomizeErroCodeImpl erroCode){
        this.message=erroCode.getMessage();
    }

    public CustomizeException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
