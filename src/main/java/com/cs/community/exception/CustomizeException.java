package com.cs.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(CustomizeErroCodeImpl erroCode){
        this.code=erroCode.getCode();
        this.message=erroCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
