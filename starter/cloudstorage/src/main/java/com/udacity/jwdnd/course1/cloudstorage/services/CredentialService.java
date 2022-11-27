package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Boolean insert(Credential credential) { return credentialMapper.insert(credential) == 1; }

    public List<Credential> selectByUser(User user) { return credentialMapper.selectByUser(user); }

    public List<Credential> selectByUserId(Integer userId) { return credentialMapper.selectByUserId(userId); }

    public Credential selectById(int credentialId) { return credentialMapper.selectById(credentialId); }

    public Credential select(Credential credential) { return credentialMapper.select(credential); }

    public Boolean update(Credential credential) { return credentialMapper.update(credential) == 1; }

    public Boolean delete(Integer credentialId, Integer userId) { return credentialMapper.delete(credentialId, userId) == 1; }

}
