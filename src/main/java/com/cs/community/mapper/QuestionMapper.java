package com.cs.community.mapper;

import com.cs.community.dto.QuestionDTO;
import com.cs.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) " +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select *from question")
    List<QuestionDTO> selectAll();

    @Select("select *from question where creator=#{id} group by gmt_create desc")
    List<QuestionDTO> selectQuestionByUserId(Long id);

    @Select("select *from question where id=#{id}")
    QuestionDTO selectQuestionById(Integer id);
}
