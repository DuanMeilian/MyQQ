package com.cqnu.dlzf.socket;

import com.cqnu.dlzf.utils.InternetUtils;
import com.cqnu.dlzf.utils.filebean.FileBean;

import java.io.*;
import java.net.Socket;

public class UploadHeadClient implements Runnable{

    private Socket socket;
    private FileBean headFile;

    /**
     *
     * @param serverIp 头像处理服务器的ip（此处本机担任所有角色）
     * @param serverPort 端口
     * @param headFile 头像图片
     * @throws IOException
     */
    public UploadHeadClient(String serverIp, int serverPort, FileBean headFile) throws IOException {
        this.headFile = headFile;

        if (InternetUtils.isReachable(serverIp)){
            socket = new Socket(serverIp,serverPort);
        }
    }

    @Override
    public void run() {
        try {
            sendImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendImage() throws IOException {
        if (socket == null || headFile.isEmpty()) return;

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(headFile);

        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
