package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final CredentialService credentialService;
    private final NoteService noteService;
    private final FileService fileService;

    public HomeController(UserService userService, CredentialService credentialService,
                          NoteService noteService, FileService fileService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @GetMapping(value = "/result")
    public String resultView() {
        return "result";
    }

    @GetMapping(value = {"/", "/home"})
    public String home(Model model, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        model.addAttribute(user);
        model.addAttribute("credentials", credentialService.selectByUser(user));
        model.addAttribute("notes", noteService.selectByUser(user));
        model.addAttribute("files", fileService.selectByUser(user));
        return "home";
    }
}
