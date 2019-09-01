package com.cs.community.controller;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import com.cs.community.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<QuestionDTO> questionItems=questionService.findAllQuestionItems();
        PageInfo<QuestionDTO> pageInfo=new PageInfo<QuestionDTO>(questionItems);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }
}
