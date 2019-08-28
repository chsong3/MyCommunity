package com.cs.community.service.impl;

import com.cs.community.mapper.QuestionMapper;
import com.cs.community.model.Question;
import com.cs.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void create(Question question) {
        questionMapper.create(question);
    }
}
