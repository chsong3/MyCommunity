package com.cs.community.service;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.model.Question;

import java.util.List;

public interface QuestionService {
    void create(Question question);

    List<QuestionDTO> findAllQuestionItems();
}
