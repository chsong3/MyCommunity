package com.cs.community.controller;

import com.cs.community.dto.CommentDTO;
import com.cs.community.dto.ResultDTO;
import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.model.Comment;
import com.cs.community.model.User;
import com.cs.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by cs on 2019/9/14 11:43
 */
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(CustomizeErroCodeImpl.NO_LOGIN);
        }
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        commentService.addComment(comment);
        return ResultDTO.okOf();
    }
}
