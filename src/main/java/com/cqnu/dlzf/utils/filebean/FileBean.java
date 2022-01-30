package com.cqnu.dlzf.utils.filebean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class FileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String fileName;
    private String fileType;
    private byte[] data;

    public FileBean() {
    }

    public FileBean(String userId, String fileName, String fileType, byte[] data) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean isEmpty(){
        return fileName == null || fileType == null || data == null;
    }

    /**
     * 通过文件获得FileBean
     * @param file
     * @return
     * @throws IOException
     */
    public static FileBean getFileBean(String userId, File file) throws IOException {
        String name = file.getName();
        String fileName = name.substring(0,name.indexOf('.'));
        String fileType = name.substring(name.indexOf('.'), name.length());

        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[fis.available()];
        fis.read(data);

        return new FileBean(userId, fileName, fileType, data);
    }
}
