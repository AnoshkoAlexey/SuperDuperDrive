package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    private final UserService userService;
    private final CredentialService credentialService;
    private final NoteService noteService;
    private final FileService fileService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, CredentialService credentialService,
                          NoteService noteService, FileService fileService,
                          EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
    }

    @GetMapping(value = "/result")
    public String resultView() {
        return "result";
    }

    @GetMapping(value = {"/", "/home"})
    public String homeView(Model model, Credential credential, Note note, File file,  Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        model.addAttribute(user);
        model.addAttribute("credentials", credentialService.selectByUser(user));
        model.addAttribute("notes", noteService.selectByUser(user));
        model.addAttribute("files", fileService.selectByUser(user));
        return "home";
    }
}
