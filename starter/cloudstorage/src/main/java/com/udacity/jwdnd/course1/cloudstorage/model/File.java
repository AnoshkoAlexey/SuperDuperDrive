package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private int fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private int userId;
    private byte[] fileData;

    public File(int fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public int getFileId() { return fileId; }

    public void setFileId(int fileId) { this.fileId = fileId; }

    public String getFileName() { return fileName; }

    public void setFileName(String filename) { this.fileName = filename; }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contenttype) {
        this.contentType = contenttype;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserid() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] filedata) {
        this.fileData = filedata;
    }
}