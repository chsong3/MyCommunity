package com.cs.community.service.impl;

import com.cs.community.dto.CommentDTO;
import com.cs.community.dto.QuestionDTO;
import com.cs.community.enums.CommentTypeEnum;
import com.cs.community.exception.CustomizeErroCodeImpl;
import com.cs.community.exception.CustomizeException;
import com.cs.community.mapper.CommentMapper;
import com.cs.community.mapper.QuestionExtensionMapper;
import com.cs.community.mapper.QuestionMapper;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.*;
import com.cs.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionExtensionMapper questionExtensionMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void create(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public List<QuestionDTO> findAllQuestionItems() {

        List<Question> questionItems = questionMapper.selectByExample(new QuestionExample());
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:questionItems){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public List<Question> findQuestionByUserId(Integer id) {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        return questionMapper.selectByExample(questionExample);
    }

    @Override
    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustomizeException(CustomizeErroCodeImpl.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建新的方法
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //更新问题
            question.setGmtModified(System.currentTimeMillis());
            int update = questionMapper.updateByPrimaryKeySelective(question);
            if (update!=1){
                throw new CustomizeException(CustomizeErroCodeImpl.QUESTION_NOT_FOUND);
            }
            //questionMapper.updateByExampleSelective(question, new QuestionExample());
        }
    }

    @Override
    public void increaseViewCount(Integer id) {
        questionExtensionMapper.updateViewCount(id);
    }

    @Override
    public List<CommentDTO> findCommentByParentId(Integer id) {

        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size()==0){
            return new ArrayList<>();
        }
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment:comments){
            CommentDTO commentDTO=new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }
}
