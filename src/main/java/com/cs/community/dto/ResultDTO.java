package com.cs.community.dto;

import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.exception.CustomizeException;
import lombok.Data;

/**
 * Created by cs on 2019/9/14 15:29
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErroCodeImpl erroCode) {
        return errorOf(erroCode.getCode(),erroCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
}
