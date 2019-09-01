package com.cs.community.controller;

import com.cs.community.mapper.QuestionMapper;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.Question;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import com.cs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cs
 * @create 2019-08-27-17:08
 */
@Controller
public class PublishController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, Model model,
                            HttpServletRequest request){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if (question.getTitle()==null || question.getTitle()==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (question.getDescription()==null || question.getDescription()==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if (question.getTag()==null || question.getTag()==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录，请登录");
            return "publish";
        }
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionService.create(question);
        return "redirect:/";
    }
}
