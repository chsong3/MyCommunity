package com.cs.community.service.impl;

import com.cs.community.enums.CommentTypeEnum;
import com.cs.community.exception.CustomizeErroCode;
import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.exception.CustomizeException;
import com.cs.community.mapper.CommentMapper;
import com.cs.community.mapper.QuestionExtensionMapper;
import com.cs.community.mapper.QuestionMapper;
import com.cs.community.model.Comment;
import com.cs.community.model.Question;
import com.cs.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;

/**
 * Created by cs on 2019/9/14 16:03
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    QuestionExtensionMapper questionExtensionMapper;
    @Override
    @Transactional
    public void addComment(Comment comment) {
        if (comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErroCodeImpl.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErroCodeImpl.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (parentComment==null){
                throw new CustomizeException(CustomizeErroCodeImpl.COMMENT_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
            }
        }else {
            //回复问题
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErroCodeImpl.QUESTION_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
                questionExtensionMapper.updateCommentCount(question);
            }
        }
    }
}
