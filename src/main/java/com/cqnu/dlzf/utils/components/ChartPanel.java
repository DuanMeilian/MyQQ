package com.cqnu.dlzf.utils.components;

import com.cqnu.dlzf.utils.factory.ImageHandlerFactory;
import com.cqnu.dlzf.utils.enums.Owner;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChartPanel extends JPanel{

    private static final long serialVersionUID = 1L;

    // 我的头像
    private JLabel my_icon;

    // 聊天区域
    private JPanel words_panel;

    // 聊天内容
    private JTextArea my_chart;

    // 头像尺寸（正方形）
    private int iconSize = 56;

    // 区域宽度
    private int panelWidth = 736;

    // 区域高度
    private int panelHeight = 80;

    // 聊天内容的宽度
    private int myChartWidth;

    // 聊天内容的高度
    private int myChartHeight;

    // 最大字宽度
    private int wordsMaxWidth;

    // 间距
    private int contentPadding = 11;

    /**
     * 创建一次聊天域
     * @param imgPath
     * @param chart_content
     * @param wordsPanelColor
     * @param owner I：头像在右边（本用户）；Friend：头像在左边（好友）
     */
    public ChartPanel(String imgPath, String chart_content, Color wordsPanelColor, Owner owner) {
        setBackground(Color.WHITE);
        setSize(panelWidth, panelHeight);
        setLayout(null);

        ImageIcon head = ImageHandlerFactory.getResizedImageIcon(imgPath, iconSize);
        my_icon = new JLabel("");
        my_icon.setIcon(head);
        my_icon.setSize(iconSize, iconSize);
        add(my_icon);

        wordsMaxWidth = panelWidth - 12 - iconSize - 12 - 35; // 必须在设置了my_con之后立马初始化
        updateMyChartWidthAndHeight(chart_content);

        words_panel = new JPanel();
        words_panel.setBackground(wordsPanelColor);
        words_panel.setLayout(null);
        add(words_panel);

        int wordsWidth = panelWidth - 12 - iconSize - 12 - myChartWidth - 9;
        words_panel.setSize(myChartWidth + contentPadding, myChartHeight + contentPadding);

        my_chart = new JTextArea(chart_content);
        my_chart.setFont(new Font("黑体", Font.PLAIN, 16));
        my_chart.setLineWrap(true); // 自动换行
        my_chart.setWrapStyleWord(true); // 断行不断字
        my_chart.setEditable(false);
        my_chart.setBackground(wordsPanelColor);
        words_panel.add(my_chart);
        my_chart.setSize(myChartWidth, myChartHeight);
        my_chart.setLocation(contentPadding, contentPadding);

        // 本用户（头像在右）
        if (owner == Owner.I) {
            setMyChartForeground(Color.WHITE);

            my_icon.setLocation(panelWidth - 12 - iconSize, 12);
            words_panel.setLocation(wordsWidth, 12);
        }else if(owner == Owner.Friend) {
            setMyChartForeground(Color.BLACK);

            my_icon.setLocation(12, 12);
            words_panel.setLocation(12 + iconSize + 12, 12);
        }
    }

    /**
     * 设置聊天内容的字体颜色
     * @param color
     */
    public void setMyChartForeground(Color color) {
        my_chart.setForeground(color);
    }

    /**
     * 根据聊天内容的字节数，计算my_chart的宽度和高度
     * 分析：my_chart的字体为16px，每个字节大约为8.17px
     * @param chart_content
     * @return
     */
    private void updateMyChartWidthAndHeight(String chart_content) {

        // 计算字符串的宽度
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("黑体", Font.PLAIN, 16);
        int textwidth = (int)(font.getStringBounds(chart_content, frc).getWidth());

        myChartWidth = textwidth + 11;
        myChartHeight = 23;

        if (myChartWidth > wordsMaxWidth) {
            int times = myChartWidth / wordsMaxWidth;
            if (times != 0 && (myChartWidth % wordsMaxWidth != 0)) {
                times++;
            }
            myChartHeight *= times;

            myChartWidth = wordsMaxWidth;
        }
    }
}
