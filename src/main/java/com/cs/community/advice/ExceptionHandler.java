package com.cs.community.advice;

import com.cs.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        //HttpStatus status = getSatus(request);
        if (e instanceof CustomizeException) {
            model.addAttribute("message",e.getMessage());
        } else {
            model.addAttribute("message", "抱歉，服务器异常！");
        }
        return new ModelAndView("error");
    }

//    private HttpStatus getSatus(HttpServletRequest request){
//        Integer statusCode=(Integer)request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode==null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
