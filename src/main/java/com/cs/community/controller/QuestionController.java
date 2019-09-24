package com.cs.community.controller;

import com.cs.community.dto.CommentDTO;
import com.cs.community.dto.QuestionDTO;
import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.exception.CustomizeException;
import com.cs.community.mapper.CommentMapper;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.Comment;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import com.cs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model, HttpServletRequest request){
        QuestionDTO questionDTO=questionService.findQuestionById(id);
        //增加访问量
        questionService.increaseViewCount(id);

        //查询问题评论信息
        List<CommentDTO> commentList = questionService.findCommentByParentId(id);
        //查询评论人
        for (CommentDTO comment:commentList){
            User user=userService.findUserById(comment.getCommentator());
            comment.setUser(user);
        }

        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("commentList",commentList);

        return "question";
    }

}
