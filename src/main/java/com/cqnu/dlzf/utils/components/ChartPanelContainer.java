package com.cqnu.dlzf.utils.components;

import javax.swing.*;
import java.awt.*;

public class ChartPanelContainer extends JPanel {

    private int scrollWidth;
    private int scrollHeight;

    // 聊天框内容的高度，初始为0，每增加一次聊天信息，加一次它的高度
    private int contentHeight = 0;

    // 当前页的更多记录
    private MoreChartPanel moreChartPanel;

    public ChartPanelContainer(){
        super();
    }

    public ChartPanelContainer(int scrollWidth, int scrollHeight){
        super();
        this.scrollWidth = scrollWidth;
        this.scrollHeight = scrollHeight;
    }

    public int getContentHeight(){
        return contentHeight;
    }

    public void setContentHeight(int contentHeight) {
        this.contentHeight = contentHeight;
    }

    public MoreChartPanel getMoreChartPanel() {
        return moreChartPanel;
    }

    @Override
    public Component add(Component comp) {
        if (comp instanceof MoreChartPanel) moreChartPanel = (MoreChartPanel) comp;

        contentHeight += comp.getHeight();

        // 大于滚动区域，开始显示滚动条
        if (contentHeight >= scrollHeight){
            setPreferredSize(new Dimension(scrollWidth-20,contentHeight));
        }

        return super.add(comp);
    }
}
