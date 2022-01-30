package com.cqnu.dlzf.upload;

import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class TestImg {

    public static void main(String[] args) throws URISyntaxException {
        String imgPath = "default.png";
        File file = new File("head_uploads/" + imgPath);
        resize(file, file,61, "PNG");
    }

    public static void resize(File originalFile, File resizedFile, int newWidth, int newHeight, String formatName) {
        try {
            BufferedImage inputBufImage = ImageIO.read(originalFile);
            ResampleOp resampleOp = new ResampleOp(newWidth, newHeight);// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,null);
            ImageIO.write(rescaledTomato, formatName, resizedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resize(File originalFile, File resizedFile, int newWidth, String formatName) {
        try {
            BufferedImage inputBufImage = ImageIO.read(originalFile);
            int oldWidth = inputBufImage.getWidth();
            int oldHeight = inputBufImage.getHeight();
            double wRatio = (double)newWidth / oldWidth;
            ResampleOp resampleOp = new ResampleOp(newWidth, (int)(oldHeight * wRatio));// 转换
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,null);
            ImageIO.write(rescaledTomato, formatName, resizedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
