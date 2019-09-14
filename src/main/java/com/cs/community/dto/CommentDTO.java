package com.cs.community.dto;

import lombok.Data;

/**
 * Created by cs on 2019/9/14 11:40
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
