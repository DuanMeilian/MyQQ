package com.cqnu.dlzf.socket;

import com.cqnu.dlzf.utils.InternetUtils;

import java.io.*;
import java.net.Socket;

public class ChartClient implements Runnable{

    private Socket socket;

    private Object chartPanel;

    public ChartClient(String serverIp, int serverPort, Object chartPanel) throws IOException {
        this.chartPanel = chartPanel;

        if (InternetUtils.isReachable(serverIp)){
            socket = new Socket(serverIp,serverPort);
        }
    }

    @Override
    public void run() {
        try {
            sendInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送对象
     * @throws IOException
     */
    private void sendInfo() throws IOException {
        if (socket == null) return;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(chartPanel);

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public Socket getSocket() {
        return socket;
    }
}
