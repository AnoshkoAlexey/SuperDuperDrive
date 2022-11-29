package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private final FileMapper fileMapper;

    public FileService(FileMapper noteMapper) {
        this.fileMapper = noteMapper;
    }

    public Boolean insert(File file) {
        if (this.isFileAvailable(file))
            return fileMapper.insert(file) == 1;
        else
            return false;
    }
    public List<File> selectByUser(User user){
        return this.fileMapper.selectByUser(user);
    }

    public List<File> selectByUserId(Integer userId){
        return this.fileMapper.selectByUserId(userId);
    }

    public File selectById(Integer fileId, Integer userId){
        return this.fileMapper.selectById(fileId, userId);
    }

    public Boolean delete(Integer fileId, Integer userId){
        return this.fileMapper.delete(fileId, userId) == 1;
    }

    public File create(MultipartFile multipartFile, User user) {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = null;
        try {
            file = new File(null, fileName, multipartFile.getContentType(), String.valueOf(multipartFile.getSize()), user.getUserId(), multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public Boolean isFileAvailable(File file) { return fileMapper.select(file).isEmpty(); }
}