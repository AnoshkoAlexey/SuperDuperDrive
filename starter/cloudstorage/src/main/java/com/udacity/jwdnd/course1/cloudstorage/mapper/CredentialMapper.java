package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO credentials (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM credentials WHERE credentialid = #{credentialId} AND userid = #{userId}")
    Credential select(Credential credential);

    @Select("SELECT * FROM credentials WHERE credentialid = #{credentialId}")
    Credential selectById(Integer credentialId);

    @Select("SELECT * FROM credentials WHERE userid = #{userId}")
    List<Credential> selectByUser(User user);

    @Select("SELECT * FROM credentials WHERE userid = #{userId}")
    List<Credential> selectByUserId(Integer userId);

    @Update("UPDATE credentials SET url = #{url}, username = #{username}, key = #{key}, password= #{password}, userid = #{userId} WHERE credentialId = #{credentialId}")
    int update(Credential credential);

    @Delete("DELETE FROM credentials WHERE credentialid = #{credentialId} and userid = #{userId}")
    int delete(Integer credentialId, Integer userId);
}
