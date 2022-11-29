package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public String insert(@ModelAttribute Note note, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        Boolean status;

        if (Objects.isNull(note.getNoteId())) {
            Note newNote = noteService.create(note, user);
            status = noteService.insert(newNote);
        } else {
            note.setUserId(user.getUserId());
            status = noteService.update(note);
        }

        if (status) {
            return "redirect:/result?success&message=The note is created";
        } else {
            return "redirect:/result?error&message=The note can not be created";
        }
    }

    @GetMapping("/delete/{noteId}")
    public String delete(@PathVariable("noteId") Integer noteId, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());

        if (noteService.delete(noteId, user.getUserId())) {
            return "redirect:/result?success&message=The note is deleted";
        } else {
            return "redirect:/result?error&message=The note can not be deleted";
        }
    }
}