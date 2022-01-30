package com.cqnu.dlzf.controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.cqnu.dlzf.bean.ChartRecord;
import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.service.ChartRecordService;
import com.cqnu.dlzf.service.UserFriendsService;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.ChartRecordServiceImpl;
import com.cqnu.dlzf.service.impl.UserFriendsServiceImpl;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.socket.ChartClient;
import com.cqnu.dlzf.socket.ChartServer;
import com.cqnu.dlzf.utils.*;
import com.cqnu.dlzf.utils.components.*;
import com.cqnu.dlzf.utils.enums.Owner;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChartHAHA extends JFrame {

    private UserService userService = new UserServiceImpl();
    private UserFriendsService userFriendsService = new UserFriendsServiceImpl();
    private ChartRecordService chartRecordService = new ChartRecordServiceImpl();

    private Map<String, JFrame> hahaFrames;

    private String userId;
    private String friendId;

    private ChartPanelContainer content_panel;
    private JTextArea textArea;

    // 显示消息记录的第几页
    private int pageNum = 1;

    // 一页显示多少条记录
    private static final int PAGE_SIZE = 20;

    public String getFriendId() {
        return friendId;
    }

    public Map<String, JFrame> getHahaFrames() {
        return hahaFrames;
    }

    public static ChartHAHA init(String listId, String userId, String friendId, Map<String, JFrame> hahaFrames,int loginPort) {
        ChartHAHA frame = new ChartHAHA(listId,userId,friendId,loginPort);
        frame.hahaFrames = hahaFrames;

        // 每个好友只能开一个聊天窗口
        hahaFrames.put("ChartHAHA" + friendId,frame);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        frame.setVisible(true);
        return frame;
    }

    public ChartHAHA(String listId, String userId, String friendId, int loginPort) {
        int scrollWidth = 755;
        int scrollHeight = 463;
        content_panel = new ChartPanelContainer(scrollWidth,scrollHeight);
        content_panel.setBackground(Color.WHITE);
        content_panel.setLayout(null);

        ChartServer server = new ChartServer(loginPort,content_panel,this);
        HAHAThreadPool.execute(server);

        this.userId = userId;
        this.friendId = friendId;

        User user = userService.getUserById(userId);
        User friend = userService.getUserById(friendId);

        Color originalColor = new Color(203,49,109);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 767, 731);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);

        JPanel contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel title_panel = new JPanel();
        title_panel.setBackground(originalColor);
        title_panel.setBounds(6, 6, 755, 50);
        contentPane.add(title_panel);
        title_panel.setLayout(null);

        JLabel friend_name_text = new JLabel(friend.getNickname());
        friend_name_text.setHorizontalAlignment(SwingConstants.CENTER);
        friend_name_text.setBounds(177, 0, 400, 50);
        friend_name_text.setFont(new Font("黑体", Font.PLAIN, 22));
        friend_name_text.setForeground(Color.WHITE);
        title_panel.add(friend_name_text);

        UsualJLabelTool tool = new UsualJLabelTool(this, title_panel.getWidth(), 45);
        JLabel close = tool.getCloseJLable(title_panel.getBackground(), new Color(254,84,57));
        title_panel.add(close);

        JLabel minimize = tool.getMinimizeJLabel(title_panel.getBackground(), new Color(241,75,140));
        title_panel.add(minimize);

        JPanel sender_panel = new JPanel();
        sender_panel.setBackground(Color.WHITE);
        sender_panel.setBounds(6, 518, 755, 207);
        contentPane.add(sender_panel);
        sender_panel.setLayout(null);

        JPanel menu_panel = new JPanel();
        menu_panel.setBackground(Color.WHITE);
        menu_panel.setBounds(0, 0, 755, 45);
        sender_panel.add(menu_panel);

        JPanel button_panel = new JPanel();
        button_panel.setBackground(Color.WHITE);
        button_panel.setBounds(0, 146, 755, 61);
        sender_panel.add(button_panel);
        button_panel.setLayout(null);

        Font button_font = new Font("黑体", Font.PLAIN, 18);
        JLabel send_button = new JLabel("发送");
        send_button.setOpaque(true);
        send_button.setForeground(Color.WHITE);
        send_button.setBackground(new Color(203,49,109));
        send_button.setFont(button_font);
        send_button.setBounds(645, 0, 90, 40);
        send_button.setHorizontalAlignment(SwingConstants.CENTER);
        button_panel.add(send_button);

        JLabel close_button = new JLabel("关闭");
        close_button.setOpaque(true);
        close_button.setForeground(Color.BLACK);
        close_button.setBackground(new Color(239,239,240));
        close_button.setFont(button_font);
        close_button.setBounds(533, 0, 90, 40);
        close_button.setHorizontalAlignment(SwingConstants.CENTER);
        close_button.setBorder(BorderFactory.createLineBorder(new Color(189,189,189), 1));
        button_panel.add(close_button);

        UsualJLabelTool.addMouseEnteredAndExitedCB(send_button, new Color(203,49,109), new Color(191,46,102));
        UsualJLabelTool.addMouseEnteredAndExitedCB(close_button, new Color(239,239,240), Color.WHITE);

        textArea = new JTextArea();
        textArea.setFont(new Font("黑体", Font.PLAIN, 16));
        textArea.setBounds(0, 45, 755, 102);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 回车事件
                if(((char)e.getKeyChar() == KeyEvent.VK_ENTER)) {
                    String aMsg = textArea.getText();
                    textArea.setText("");

                    HAHAThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            renderMyChartOnce(user.getImage(), aMsg, originalColor, Owner.I);
                            chartOnce(userId, friendId, aMsg);
                        }
                    });
                    renderClientChartOnce(user,aMsg);
                }
            }
        });
        sender_panel.add(textArea);

        JScrollPane content_scrollPane = new JScrollPane(content_panel);
        content_scrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        content_scrollPane.setBounds(5, 55, scrollWidth, scrollHeight);
        content_scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(content_scrollPane);

        send_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String aMsg = textArea.getText();
                if ("".equals(aMsg)) return;
                textArea.setText("");

                HAHAThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        renderMyChartOnce(user.getImage(), aMsg, originalColor, Owner.I);
                        chartOnce(userId, friendId, aMsg);
                    }
                });
                renderClientChartOnce(user,aMsg);
            }
        });

        // 进入界面就获得焦点
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                textArea.requestFocus();
            }
        });

        renderChartContentPanel(originalColor);
    }

    /**
     * 渲染一次我的发送信息
     * @param imgPath
     * @param aMsg
     * @param bgColor
     */
    private void renderMyChartOnce(String imgPath, String aMsg, Color bgColor, Owner owner){
        if (owner == Owner.Friend) bgColor = new Color(227,227,227);
        ChartPanel myChartPanel = new ChartPanel(imgPath, aMsg, bgColor, owner);
        myChartPanel.setLocation(0, content_panel.getContentHeight());
        content_panel.add(myChartPanel);

        // 注意：一定要刷新界面！！！！！！
        repaint();
    }

    /**
     * 渲染好友客户端的聊天信息
     * @param aMsg
     */
    private void renderClientChartOnce(User user,String aMsg){
        User friend = userService.getUserById(friendId);
        String serverIp = friend.getIp();
        Integer serverPort = friend.getPort();
        Integer state = friend.getState();
        ChartClient client = null;
        try {
            ChartPanel friendChartPanel = new ChartPanel(user.getImage(), aMsg, new Color(227,227,227), Owner.Friend);
            client = new ChartClient(serverIp,serverPort,friendChartPanel);
            if (client.getSocket() != null && state == 1){
                HAHAThreadPool.execute(client);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 一次通讯
     * @param userId
     * @param friendId
     * @param aMsg
     */
    private void chartOnce(String userId, String friendId, String aMsg){
        ChartRecord chartRecord = new ChartRecord();
        chartRecord.setRecordId(MyBatisUtils.getUUID());
        chartRecord.setSenderId(userId);
        chartRecord.setreceiverId(friendId);
        chartRecord.setContent(aMsg);
        chartRecord.setTime(new Date(System.currentTimeMillis()));
        chartRecordService.chartOne(chartRecord);
        userFriendsService.updateNewest(chartRecord);
    }

    /**
     * 渲染和好友的聊天信息
     * @param originalColor
     */
    public void renderChartContentPanel(Color originalColor){
        PageHelper.startPage(pageNum, PAGE_SIZE);

        List<ChartRecord> chartRecords = chartRecordService.selectMyChartWithFriend(userId, friendId);

        PageInfo<ChartRecord> pageInfo = new PageInfo<>(chartRecords);
        // 不是最后一页才显示更多信息提示
        int totalPages = pageInfo.getPages();
        if (pageNum != totalPages){
            MoreChartPanel moreChartPanel = new MoreChartPanel();
            moreChartPanel.setLocation(0, content_panel.getContentHeight());
            moreChartPanel.setChartHAHA(this);
            moreChartPanel.setContainer(content_panel);
            content_panel.add(moreChartPanel);
        }
        pageNum++;

        Collections.reverse(chartRecords);
        for (int i = 0; i < chartRecords.size(); i++) {
            ChartRecord record = chartRecords.get(i);
            // 第一条信息必须显示时间（显示第一条记录的）
            if (i==0) renderChartTime(record.getTime());

            String senderId = record.getSenderId();
            if (senderId != null && senderId.equals(userId)){
                User user = userService.getUserById(userId);
                renderMyChartOnce(user.getImage(), record.getContent(), originalColor, Owner.I);
            }
            else if(senderId != null && senderId.equals(friendId)){
                User friend = userService.getUserById(friendId);
                renderMyChartOnce(friend.getImage(), record.getContent(), new Color(227,227,227), Owner.Friend);
            }

            if (i == (chartRecords.size() - 1)) return;
            ChartRecord nextRecord = chartRecords.get(i + 1);
            long recordTime = record.getTime().getTime();
            long nextTime = nextRecord.getTime().getTime();
            // 如果前一条记录和后一条记录的发布时间差大于等于十分钟，显示后一条记录的时间信息
            if (nextTime - recordTime >= 1000 * 60 * 10){
                renderChartTime(nextRecord.getTime());
            }
        }
    }

    /**
     * 渲染聊天界面的时间信息
     * @param time
     */
    private void renderChartTime(Date time){
        TimePanel time_panel = new TimePanel(735,30,time);
        time_panel.setLocation(0, content_panel.getContentHeight());
        content_panel.add(time_panel);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
