package com.cqnu.dlzf.socket;

import com.cqnu.dlzf.controller.ChartHAHA;
import com.cqnu.dlzf.utils.components.ChartPanel;
import com.cqnu.dlzf.utils.components.ChartPanelContainer;
import com.cqnu.dlzf.utils.components.FriendPanel;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChartServer implements Runnable{

    private int port;
    private ChartPanelContainer content_panel;
    private ChartHAHA chartHAHA;

    private Socket client;

    public ChartServer(int port, ChartPanelContainer content_panel, ChartHAHA chartHAHA){
        this.port = port;
        this.content_panel = content_panel;
        this.chartHAHA = chartHAHA;
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

    private void processSocket() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        ChartPanel chartPanel = (ChartPanel)objectInputStream.readObject();

        int contentHeight = content_panel.getContentHeight();
        chartPanel.setLocation(0, contentHeight);
        content_panel.add(chartPanel);
        chartHAHA.repaint();
        closeMe();
    }

    /**
     * 关闭当前客户机与服务器的连接
     * @throws IOException
     */
    public void closeMe() throws IOException {
        client.close();
    }
}
