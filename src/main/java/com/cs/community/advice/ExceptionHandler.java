package com.cs.community.advice;

import com.alibaba.fastjson.JSON;
import com.cs.community.dto.ResultDTO;
import com.cs.community.exception.CustomizeErroCode;
import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.exception.CustomizeException;
import com.sun.javafx.webkit.CursorManagerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionHandler {
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            //返回json
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErroCodeImpl.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            writer.write(JSON.toJSONString(resultDTO));
            writer.close();
            return null;
        }else {
            //错误页面的跳转
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message",CustomizeErroCodeImpl.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
