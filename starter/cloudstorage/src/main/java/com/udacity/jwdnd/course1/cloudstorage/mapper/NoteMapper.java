package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO notes (notetitle,  notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer insert(Note note);

    @Select("SELECT * FROM notes WHERE noteid = #{noteId} AND userid = #{userId}")
    Note selectById(Integer noteId, Integer userId);

    @Select("SELECT * FROM notes WHERE noteid = #{noteId}")
    List<Note> select(Note note);

    @Select("SELECT * FROM notes WHERE userid = #{userId}")
    List<Note> selectByUserId(Integer userId);

    @Select("SELECT * FROM notes WHERE userid = #{userId}")
    List<Note> selectByUser(User user);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(Note note);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId} AND userid = #{userId}")
    int delete(Integer noteId, Integer userId);

}