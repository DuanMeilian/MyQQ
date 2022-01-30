package com.cqnu.dlzf.utils.tool;

import com.cqnu.dlzf.controller.*;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UsualJLabelTool {

    // 应用窗体
    private JFrame frame;

    // 按钮的尺寸（正方形）
    private int labelSize;

    // 所在块的宽度
    private int panelWidth;

    public UsualJLabelTool(JFrame frame, int panelWidth, int labelSize) {
        this.panelWidth = panelWidth;
        this.labelSize = labelSize;
        this.frame = frame;
    }

    /**
     * 添加移入背景颜色变为changedColor，移出颜色变回origialColor
     * @param text
     * @param origialColor
     * @param changedColor
     */
    public static void addMouseEnteredAndExitedCB(JLabel text, Color origialColor, Color changedColor) {
        text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                text.setBackground(changedColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                text.setBackground(origialColor);
            }
        });
    }

    /**
     * 添加移入字体颜色变为changedColor，移出颜色变回origialColor
     * @param text
     * @param origialColor
     * @param changedColor
     */
    public static void addMouseEnteredAndExitedCF(JLabel text, Color origialColor, Color changedColor) {
        text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                text.setForeground(changedColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                text.setForeground(origialColor);
            }
        });
    }

    /**
     * 获取最小化按钮
     * @param originalColor
     * @param changedColor
     * @return
     */
    public JLabel getMinimizeJLabel(Color originalColor, Color changedColor) {
        JLabel minimize = new JLabel("一");
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setForeground(Color.WHITE);
        minimize.setFont(new Font("等线", Font.BOLD, 20));
        minimize.setBounds(panelWidth-labelSize*2, 0, labelSize, labelSize);
        minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                minimize.setBackground(originalColor);

                // 最小化
                frame.setExtendedState(JFrame.ICONIFIED);
            }

            // 移入变为浅色
            @Override
            public void mouseEntered(MouseEvent e) {
                minimize.setOpaque(true);
                minimize.setBackground(changedColor);
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                minimize.setBackground(originalColor);
            }
        });
        return minimize;
    }

    /**
     * 获取最大化按钮
     * @param originalColor
     * @param changedColor
     * @return
     */
    public JLabel getCloseJLable(Color originalColor, Color changedColor) {
        JLabel close = new JLabel("X");
        close.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setForeground(Color.WHITE);
        close.setBounds(panelWidth-labelSize, 0, labelSize, labelSize);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 先隐藏窗口，再释放资源，就不会造成卡顿
                frame.setVisible(false);
                frame.dispose();

                if (frame instanceof EditUserHAHA){
                    EditUserHAHA editUserHAHA = (EditUserHAHA)frame;
                    Map<String, JFrame> hahaFrames = editUserHAHA.getHahaFrames();
                    hahaFrames.put("EditUserHAHA",null);
                }
                else if (frame instanceof UserInfoHAHA){
                    UserInfoHAHA userInfoHAHA = (UserInfoHAHA)frame;
                    Map<String, JFrame> hahaFrames = userInfoHAHA.getHahaFrames();
                    hahaFrames.put("UserInfoHAHA",null);
                }
                else if(frame instanceof ChartHAHA){
                    ChartHAHA chartHAHA = (ChartHAHA)frame;
                    Map<String, JFrame> hahaFrames = chartHAHA.getHahaFrames();
                    hahaFrames.put("ChartHAHA" + chartHAHA.getFriendId(),null);
                }
                else if(frame instanceof AddFriendsHAHA){
                    AddFriendsHAHA addFriendsHAHA = (AddFriendsHAHA)frame;
                    Map<String, JFrame> hahaFrames = addFriendsHAHA.getHahaFrames();
                    hahaFrames.put("AddFriendsHAHA",null);
                }
                else if (frame instanceof UploadHeadHAHA){
                    UploadHeadHAHA uploadHeadHAHA = (UploadHeadHAHA)frame;
                    Map<String, JFrame> hahaFrames = uploadHeadHAHA.getHahaFrames();
                    hahaFrames.put("UploadHeadHAHA",null);
                }
            }

            // 移入背景变为橙色
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setOpaque(true);
                close.setBackground(changedColor);
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                close.setBackground(originalColor);
            }
        });
        return close;
    }

    /**
     * 获取输入框左侧标题
     * @param text
     * @param r
     * @return
     */
    public static JLabel getJLabelTitle(String text, Rectangle r) {
        JLabel title = new JLabel(text);
        title.setForeground(new Color(140,140,140));
        title.setFont(new Font("黑体", Font.PLAIN, 15));
        title.setBounds(r);
        return title;
    }
}
