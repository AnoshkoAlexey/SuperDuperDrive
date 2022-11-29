package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM files WHERE fileid = #{fileId} AND userid = #{userId}")
    File selectById(Integer fileId, Integer userId);

    @Select("SELECT * FROM files WHERE filename = #{fileName} AND userid = #{userId}")
    List<File> selectByFileName(String fileName, Integer userId);

    @Select("SELECT * FROM files WHERE filename = #{fileName}")
    List<File> select(File file);

    @Select("SELECT * FROM files WHERE userid = #{userId}")
    List<File> selectByUser(User user);

    @Select("SELECT * FROM files WHERE userid = #{userId}")
    List<File> selectByUserId(Integer userId);

    @Delete("DELETE FROM files WHERE fileid = #{fileId} AND userid = #{userId}")
    int delete(Integer fileId, Integer userId);

}
