package com.cs.community.exception;

public enum CustomizeErroCodeImpl implements CustomizeErroCode {
    QUESTION_NOT_FOUND("抱歉，该问题已不存在！");
    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErroCodeImpl(String message){
        this.message=message;
    }
}
