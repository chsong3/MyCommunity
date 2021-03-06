package com.cs.community.controller;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.model.Question;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import com.cs.community.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")//动态获取参数
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,
                          Model model, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }


        if ("question".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }
        if ("repie".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }
        Integer id = user.getId();
        List<Question>  questionList=questionService.findQuestionByUserId(id);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questionList){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        //PageHelper.startPage(pageNum,5);
        //PageInfo<QuestionDTO> pageInfo=new PageInfo<QuestionDTO>();
        //pageInfo.setList(questionList);
        model.addAttribute("questionList",questionDTOList);
        return "profile";
    }

}
