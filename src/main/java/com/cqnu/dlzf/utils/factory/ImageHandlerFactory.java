package com.cqnu.dlzf.utils.factory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageHandlerFactory {

    /**
     * 将图片转换成黑白，并指定缩放尺寸
     * @param imgPath
     * @param newWidth
     * @return
     *
     * @author:w93223010
     * https://blog.csdn.net/w93223010/article/details/8265720?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164076852816780269852853%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=164076852816780269852853&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-8265720.pc_search_em_sort&utm_term=Java%E5%B0%86%E5%9B%BE%E7%89%87%E8%BD%AC%E9%BB%91%E7%99%BD&spm=1018.2226.3001.4187
     */
    public static ImageIcon changedBWImage(String imgPath, int newWidth) {
        ImageIcon icon;
        try {
            File fileImg = new File(imgPath);
            Image image = ImageIO.read(fileImg);
            int srcH = image.getHeight(null);
            int srcW = image.getWidth(null);
            BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);
            bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);
            bufferedImage = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);
            Image resultImage = bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), Image.SCALE_DEFAULT);
            icon = new ImageIcon(resultImage);

            // 改变尺寸
            int oldWidth = bufferedImage.getWidth();
            int oldHeight = bufferedImage.getHeight();
            double wRatio = (double)newWidth / oldWidth;
            icon.setImage(icon.getImage().getScaledInstance(newWidth,(int)(oldHeight*wRatio), Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("图片黑白转换出错！", e);
        }
        return icon;
    }

    /**
     * 获取新宽度的头像图片
     * @param headPath
     * @param newWidth
     * @return
     */
    public static ImageIcon getResizedImageIcon(String headPath, int newWidth){
        BufferedImage inputBufImage = null;
        ImageIcon userHead = null;
        try{
//            headPath = headPath.substring(headPath.indexOf('/')+1,headPath.length());
//            inputBufImage = ImageIO.read(new File(headPath));

            inputBufImage = ImageIO.read(new File(headPath));
            int oldWidth = inputBufImage.getWidth();
            int oldHeight = inputBufImage.getHeight();
            double wRatio = (double)newWidth / oldWidth;
            userHead = new ImageIcon(headPath);
            userHead.setImage(userHead.getImage().getScaledInstance(newWidth,(int)(oldHeight*wRatio), Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHead;
    }

    public static ImageIcon getResizedImageIcon(URL headURL, int newWidth){
        BufferedImage inputBufImage = null;
        ImageIcon userHead = null;
        try{
//            headPath = headPath.substring(headPath.indexOf('/')+1,headPath.length());
////            inputBufImage = ImageIO.read(new File(headPath));
            inputBufImage = ImageIO.read(headURL);
            int oldWidth = inputBufImage.getWidth();
            int oldHeight = inputBufImage.getHeight();
            double wRatio = (double)newWidth / oldWidth;
            userHead = new ImageIcon(headURL);
            userHead.setImage(userHead.getImage().getScaledInstance(newWidth,(int)(oldHeight*wRatio), Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHead;
    }
}
