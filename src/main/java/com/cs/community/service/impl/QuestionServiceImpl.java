package com.cs.community.service.impl;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.mapper.QuestionMapper;
import com.cs.community.mapper.UserMapper;
import com.cs.community.model.Question;
import com.cs.community.model.QuestionExample;
import com.cs.community.model.User;
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
        Question questionDTO = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO1 = new QuestionDTO();
        questionDTO1.setUser(userMapper.selectByPrimaryKey(questionDTO.getCreator()));
        return questionDTO1;
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
            questionMapper.updateByExampleSelective(question, new QuestionExample());
        }
    }
}
