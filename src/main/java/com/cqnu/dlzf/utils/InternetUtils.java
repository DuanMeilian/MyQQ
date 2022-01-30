package com.cqnu.dlzf.utils;

import org.apache.ibatis.io.Resources;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

public class InternetUtils {

    /**
     * 获取本机真实IP
     * @return
     */
    public static String getLocalIp() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface nif = netInterfaces.nextElement();
                Enumeration<InetAddress> InetAddress = nif.getInetAddresses();
                while (InetAddress.hasMoreElements()) {
                    String ip = InetAddress.nextElement().getHostAddress();
                    if (ip.startsWith("192.168")) {
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    /**
     * 获取port.properties中的全局属性port：一个HAHA，一个端口号，从9000开始
     * @return
     */
    public static int getPort(){
        return portOperation(1);
    }
    /**
     * 用户离线，减去端口号
     */
    public static void reducePort(){
        portOperation(-1);
    }

    private static int portOperation(int value){
        int port = -1;
        FileReader reader = null;
        try {
            String path = "C:\\head_uploads\\port.properties";
            reader = new FileReader(path);

            Properties properties = new Properties();
            properties.load(reader);
            port = Integer.parseInt(properties.getProperty("port"));
            properties.setProperty("port",String.valueOf(port + value));

            FileOutputStream fos = new FileOutputStream(path);
            properties.store(fos,"new port");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return port;
    }

    /**
     * 测试是否能ping通指定ip的网络
     * @param serverIp
     * @return
     * @throws IOException
     */
    public static boolean isReachable(String serverIp) throws IOException {
        InetAddress otherAddress = InetAddress.getByName(serverIp);
        return otherAddress.isReachable(1);
    }
}
