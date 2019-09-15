package com.cs.community.exception;

import com.cs.community.dto.ResultDTO;

public enum CustomizeErroCodeImpl implements CustomizeErroCode {

    QUESTION_NOT_FOUND(2001,"抱歉，该问题已不存在！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或者评论进行回复"),
    NO_LOGIN(2003,"请登录后重试"),
    SYS_ERROR(2004,"服务器异常！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论已不存在！");

    private Integer code;
    private String message;


    CustomizeErroCodeImpl(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
