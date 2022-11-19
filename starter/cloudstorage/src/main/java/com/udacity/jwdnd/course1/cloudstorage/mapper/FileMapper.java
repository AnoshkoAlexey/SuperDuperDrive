package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM files WHERE userid = #{userId}")
    List<File> getFilesByUserId(int userId);

    @Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM files WHERE fileid = #{fileId}")
    void deleteFile(int fileId);

    @Select("SELECT * FROM files WHERE fileid = #{fileId}")
    File getFileById(int fileId);

    @Select("SELECT * FROM files WHERE userid = #{userId} AND filename = #{fileName}")
    List<File> getFileByFilename(int userId, String fileName);
}
