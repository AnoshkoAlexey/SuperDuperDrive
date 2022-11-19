package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM notes WHERE userid = #{userId}")
    Note[] getNotesByUserId(Integer userId);

    @Insert("INSERT INTO notes (notetitle,  notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId}")
    void deleteNote(int noteId);

    @Select("SELECT * FROM notes WHERE noteid = #{noteId}")
    Note getNoteById(int noteId);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{notedescription} WHERE noteid = #{noteId}")
    void updateNote(Note note);
}