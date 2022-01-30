package com.cqnu.dlzf.utils.components;

import com.cqnu.dlzf.controller.ChartHAHA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoreChartPanel extends JPanel {

    private ChartHAHA chartHAHA;
    private ChartPanelContainer container;

    public MoreChartPanel(){
        setBackground(Color.WHITE);
        setBounds(0, 0, 755, 45);
        setLayout(null);

        JLabel more_chart = new JLabel("查看更多消息");
        more_chart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                more_chart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(203,49,109)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                more_chart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
            }
        });
        more_chart.setForeground(new Color(203,49,109));
        more_chart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        more_chart.setFont(new Font("黑体", Font.PLAIN, 14));
        more_chart.setBounds(348, 15, 86, 16);
        more_chart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                moreRender();
            }
        });
        add(more_chart);

        JLabel icon = new JLabel("");
        icon.setIcon(new ImageIcon(ChartHAHA.class.getResource("/img/time.png")));
        icon.setBounds(325, 13, 17, 18);
        add(icon);
    }

    public ChartHAHA getChartHAHA() {
        return chartHAHA;
    }

    public void setChartHAHA(ChartHAHA chartHAHA) {
        this.chartHAHA = chartHAHA;
    }

    public ChartPanelContainer getContainer() {
        return container;
    }

    public void setContainer(ChartPanelContainer container) {
        this.container = container;
    }

//    private void moreRender(){
//        setVisible(false);
//        int contentHeight = container.getContentHeight();
//        Component[] components = container.getComponents();
//        for (Component component : components) {
//            if (component instanceof JPanel){
//                JPanel panel = (JPanel)component;
//                int x = panel.getX();
//                int y = panel.getY();
//                panel.setLocation(panel.getX(), panel.getY() + contentHeight);
//                if (contentHeight >= container.getScrollHeight()){
//                    container.setPreferredSize(new Dimension(container.getScrollWidth()-20,panel.getY() + contentHeight));
//                }
//            }
//        }
//        chartHAHA.repaint();
//
//        container.setContentHeight(0);
//        chartHAHA.renderChartContentPanel(new Color(203,49,109));
//
//        container.setContentHeight(contentHeight + container.getContentHeight());
//    }

    /**
     * 渲染更多信息，两种方案：
     * 一、清空重新添加页数
     * 缺点：反复发sql
     * 二、把已经渲染的内容放到下面，把要查询的页放到上面
     */
    private void moreRender(){
        container.setContentHeight(0);
        container.removeAll();
        chartHAHA.repaint();

        int toBeRenderedPages = chartHAHA.getPageNum();
        for (int i = 0; i < toBeRenderedPages; i++) {
            chartHAHA.renderChartContentPanel(new Color(203,49,109));
            MoreChartPanel moreChartPanel = container.getMoreChartPanel();
            moreChartPanel.setVisible(false);

            /*
                本身应该减一，因为是倒数页数，比如现在已经渲染4页，那么应该调用5次renderChartContentPanel，
                然后5 4 3 2 1，然而renderChartContentPanel方法内部有自增一，所以要减去2
             */
            chartHAHA.setPageNum(chartHAHA.getPageNum() - 2);
        }
    }
}
