package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/submit")
    public String insert(@ModelAttribute Credential credential, Authentication authentication){

        User user = userService.selectByName(authentication.getName());

        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setUserId(user.getUserId());

        Boolean status;

        if (Objects.isNull(credential.getCredentialId())) {
            status = credentialService.insert(credential);
        } else {
            status = credentialService.update(credential);
        }

        if (status) {
            return "redirect:/result?success&message=The credential is saved";
        } else {
            return "redirect:/result?error&message=The credential can not be saved";
        }
    }

    @GetMapping("/delete/{credentialid}")
    public String delete(@PathVariable("credentialid") Integer credentialId, Authentication authentication){

        User user = userService.selectByName(authentication.getName());

        Boolean status = credentialService.delete(credentialId, user.getUserId());

        if (status) {
            return "redirect:/result?success&message=The credential is deleted";
        } else {
            return "redirect:/result?error&message=The credential can not be deleted";
        }
    }
}