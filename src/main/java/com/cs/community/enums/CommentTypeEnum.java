package com.cs.community.enums;

/**
 * Created by cs on 2019/9/14 15:45
 */
public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
