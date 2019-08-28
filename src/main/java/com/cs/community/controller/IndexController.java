package com.cs.community.controller;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import com.cs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    User user=userService.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }

        List<QuestionDTO> questionItems=questionService.findAllQuestionItems();
        model.addAttribute("questionItems",questionItems);
        return "index";
    }
}
