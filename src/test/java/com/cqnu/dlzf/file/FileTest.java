package com.cqnu.dlzf.file;

import com.cqnu.dlzf.controller.UserHAHA;
import org.junit.Test;

import java.io.*;
import java.net.URL;

public class FileTest {

    @Test
    public void fileTest1(){
        File file = new File("head_uploads/default.png");
        String name = file.getName();
        System.out.println("文件名：" + name.substring(0,name.indexOf('.')));
        System.out.println("文件类型：" + name.substring(name.indexOf('.'), name.length()));
    }


    @Test
    public void fileTest2(){
        File file = new File(getClass().getResource("/head_uploads/default.png").getPath());
        System.out.println(file.isFile());

        // D:\ideaproject\MyQQ\target\classes\head_uploads
        String path = UserHAHA.class.getResource("/").getPath() + "/head_uploads/test.png";
        try(FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path)){

            byte[] data = new byte[fis.available()];
            fis.read(data);
            fos.write(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileTest3(){
        URL resource = getClass().getResource("/head_uploads/default.png");
        System.out.println(resource.getPath());
        System.out.println("String path = C:\\\\head_uploads\\\\default.png\\");
    }
}
