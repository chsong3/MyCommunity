package com.cs.community.mapper;

import com.cs.community.model.Question;

public interface QuestionExtensionMapper {

    void updateViewCount(Integer id);

    void updateCommentCount(Question question);
}
