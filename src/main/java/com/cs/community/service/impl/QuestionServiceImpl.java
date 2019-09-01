package com.cs.community.service.impl;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.mapper.QuestionMapper;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.Question;
import com.cs.community.model.User;
import com.cs.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void create(Question question) {
        questionMapper.create(question);
    }

    @Override
    public List<QuestionDTO> findAllQuestionItems() {

        List<QuestionDTO> questionItems = questionMapper.selectAll();
        for (QuestionDTO question:questionItems){
            User user=userMapper.findUserById(question.getCreator());
            question.setUser(user);
        }


        return questionItems;
    }

    @Override
    public List<QuestionDTO> findQuestionByUserId(Long id) {
        return questionMapper.selectQuestionByUserId(id);
    }

    @Override
    public QuestionDTO findQuestionById(Integer id) {
        QuestionDTO questionDTO = questionMapper.selectQuestionById(id);
        questionDTO.setUser(userMapper.findUserById(questionDTO.getCreator()));
        return questionDTO;
    }
}
