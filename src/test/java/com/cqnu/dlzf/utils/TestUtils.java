package com.cqnu.dlzf.utils;

import com.cqnu.dlzf.utils.enums.AnimalSign;
import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestUtils {

    @Test
    public void chartTimeFormatTest(){
        HAHAFormat hf = new HAHAFormat();
        String format = hf.chartTimeFormat(new Date(System.currentTimeMillis()));
        System.out.println(format);
    }

    @Test
    public void testPortReduce(){
        InternetUtils.reducePort();
    }

    @Test
    public void testPortAdd() throws IOException {
        int port = InternetUtils.getPort();
        System.out.println(port);
    }

    @Test
    public void testIp() throws UnknownHostException {
        String ip = InternetUtils.getLocalIp();
        System.out.println(ip);
    }

    @Test
    public void testGetStringWidth(){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("黑体", Font.PLAIN, 16);
        int textwidth = (int)(font.getStringBounds("a", frc).getWidth());
        System.out.println(textwidth);
    }

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testSDF() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
        Date date = sdf.parse("2021年12月22日");
        System.out.println(sdf.format(date));
    }

    @Test
    public void createId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();

        String s = str.replaceAll("-", "");
        System.out.println(s);
    }

    @Test
    public void testSimpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time = new Date(System.currentTimeMillis());
        System.out.println(sdf.format(time));
    }

    @Test
    public void testAnimalSign(){
        AnimalSign animalSign = AnimalSign.getAnimalSign(2021);
        System.out.println(Objects.requireNonNull(animalSign).getName());
    }

    @Test
    public void testUserInfoFormat(){
        HAHAFormat format = new HAHAFormat();
        //String userInfoFormat = format.userInfoFormat("女", new Date(System.currentTimeMillis()));
        String userInfoFormat = format.userInfoFormat("女", null);
        System.out.println("".equals(userInfoFormat));
    }
}