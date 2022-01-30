package com.cqnu.dlzf.socket;

import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String [] args)
    {
        String serverIp = "192.168.43.83";
        int port = 8400;
        try
        {
            System.out.println("连接到主机：" + serverIp + "，端口号：" + port);

            InetAddress serverAddress = InetAddress.getByName(serverIp);
            Socket client = new Socket(serverAddress, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应：" + in.readUTF());

            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
