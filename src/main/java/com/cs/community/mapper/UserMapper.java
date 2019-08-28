package com.cs.community.mapper;

import com.cs.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatar_url)" +
            "value(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    //保存登录的用户
    void insert(User user);
    //查找user,通过token
    @Select("select * from user where token=#{token}")
    User findByToken(String token);
    //通过accountId查找user
    @Select("select * from user where id=#{creator}")
    User findUserById(String creator);
}