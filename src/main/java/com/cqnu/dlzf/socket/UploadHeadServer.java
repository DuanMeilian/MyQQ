package com.cqnu.dlzf.socket;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.MyBatisUtils;
import com.cqnu.dlzf.utils.filebean.FileBean;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 * 头像处理服务器
 */
public class UploadHeadServer implements Runnable {

    private int port;
    private Socket client;

    public static void main(String[] args) {
        HAHAThreadPool.execute(new UploadHeadServer(9888));
    }

    public UploadHeadServer(int port){
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            while(true){
                client = serverSocket.accept();

                processSocket();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    private void processSocket() throws IOException, ClassNotFoundException {
////        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
////        FileBean headFile = (FileBean)objectInputStream.readObject();
////
////        // 1.将文件存储到磁盘
////        String fileName = MyBatisUtils.getUUID() + headFile.getFileType();
////
////        String newHeadPath = "/head_uploads/" + fileName;
////        String absolutePath = getClass().getResource("/") + newHeadPath;
////        FileOutputStream fos = new FileOutputStream(absolutePath);
////        fos.write(headFile.getData());
////        fos.flush();
////
////        // 文件确实存储到磁盘
////        if (new File(absolutePath).isFile()){
////            // 2.保存路径到数据库
////            UserService userService = new UserServiceImpl();
////            User upHeadUser = new User();
////            upHeadUser.setUserId(headFile.getUserId());
////            upHeadUser.setImage(newHeadPath);
////            userService.updateUser(upHeadUser);
////        }
////    }
    private void processSocket() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        FileBean headFile = (FileBean)objectInputStream.readObject();

        // 1.将文件存储到磁盘
        String fileName = MyBatisUtils.getUUID() + headFile.getFileType();

        String newHeadPath = "C:\\head_uploads\\" + fileName;
        FileOutputStream fos = new FileOutputStream(newHeadPath);
        fos.write(headFile.getData());
        fos.flush();

        // 2.保存路径到数据库
        if (new File(newHeadPath).isFile()){
            UserService userService = new UserServiceImpl();
            User upHeadUser = new User();
            upHeadUser.setUserId(headFile.getUserId());
            upHeadUser.setImage(newHeadPath);
            userService.updateUser(upHeadUser);
        }
    }
}
