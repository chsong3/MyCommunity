package com.cs.community.service;

import com.cs.community.dto.CommentDTO;
import com.cs.community.dto.QuestionDTO;
import com.cs.community.mapper.CommentMapper;
import com.cs.community.model.Comment;
import com.cs.community.model.Question;

import java.util.List;

public interface QuestionService {
    void create(Question question);

    List<QuestionDTO> findAllQuestionItems();

    List<Question> findQuestionByUserId(Integer id);

    QuestionDTO findQuestionById(Integer id);

    void createOrUpdate(Question question);

    void increaseViewCount(Integer id);

    List<CommentDTO> findCommentByParentId(Integer id);
}
