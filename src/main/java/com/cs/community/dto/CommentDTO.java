package com.cs.community.dto;

import com.cs.community.model.User;
import lombok.Data;

/**
 * Created by cs on 2019/9/14 11:40
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
    private Long likeCount;
    private User user;
    private Integer id;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
}
