package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String insert(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        File file = fileService.create(multipartFile, user);

        Boolean status = fileService.insert(file);

        if (status) {
            return "redirect:/result?success&message=The file is uploaded";
        } else {
            return "redirect:/result?error&message=The file can not be uploaded";
        }

    }

    @GetMapping("/fileId}")
    public ResponseEntity<byte[]> selectById(@PathVariable("fileId") Integer fileId, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        File file = fileService.selectById(fileId, (user.getUserId()));

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }

    @GetMapping("/delete/{fileId}")
    public String delete(@PathVariable("fileId") Integer fileId, Authentication authentication) {

        User user = userService.selectByName(authentication.getName());
        Boolean status = fileService.delete(fileId, user.getUserId());

        if (status) {
            return "redirect:/result?success&message=The file is deleted";
        } else {
            return "redirect>/result?error&message=The file can not be deleted";
        }
    }


}