package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT * FROM USERS WHERE username = #{userid}")
    User selectById(Integer userId);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User selectByName(String username);

    @Delete("DELETE FROM users WHERE userid = #{id}")
    void delete(Integer id);
}
