package com.cs.community.controller;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model, HttpServletRequest request){
        QuestionDTO questionDTO=questionService.findQuestionById(id);

        //增加访问量
        questionService.increaseViewCount(id);

        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }

}
