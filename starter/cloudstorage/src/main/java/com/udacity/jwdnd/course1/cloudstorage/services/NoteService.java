package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note create(Note note, User user){
        note = new Note(null, note.getNoteTitle(), note.getNoteDescription(), user.getUserId());
        return note;
    }

    public Boolean insert(Note note) {
        if (noteMapper.select(note).isEmpty()) {
            return noteMapper.insert(note) == 1;
        } else {
            return false;
        }
    }

    public List<Note> selectByUserId(Integer userId){
        return noteMapper.selectByUserId(userId);
    }

    public List<Note> selectByUser(User user) { return noteMapper.selectByUser(user); }

    public Note selectById(Integer noteId, Integer userId){
        return noteMapper.selectById(noteId, userId);
    }
    public Boolean update(Note note){ return noteMapper.updateNote(note) == 1; }

    public Boolean delete(Integer noteId, Integer userId){
        return noteMapper.delete(noteId, userId) == 1;
    }

}