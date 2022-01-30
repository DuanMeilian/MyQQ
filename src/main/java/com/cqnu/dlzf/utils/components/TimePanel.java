package com.cqnu.dlzf.utils.components;

import com.cqnu.dlzf.utils.HAHAFormat;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.*;

public class TimePanel extends JPanel{

    public TimePanel(int width, int height, Date chartDate) {
        setBackground(Color.WHITE);
        setSize(width, height);
        setLayout(null);

        HAHAFormat hahaFormat = new HAHAFormat();
        String chartTime = hahaFormat.chartTimeFormat(chartDate);
        JLabel chart_time_text = new JLabel(chartTime);
        chart_time_text.setHorizontalAlignment(SwingConstants.CENTER);
        chart_time_text.setBounds(306, 7, 137, 15);
        chart_time_text.setForeground(new Color(135,135,135));
        chart_time_text.setFont(new Font("黑体", Font.PLAIN, 14));
        add(chart_time_text);
    }
}
