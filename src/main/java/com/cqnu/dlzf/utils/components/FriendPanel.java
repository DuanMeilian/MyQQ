package com.cqnu.dlzf.utils.components;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;
import com.cqnu.dlzf.controller.ChartHAHA;
import com.cqnu.dlzf.controller.UserHAHA;
import com.cqnu.dlzf.utils.HAHAFormat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

// 好友框
public class FriendPanel extends JPanel{

    private static final long serialVersionUID = 1L;

    private UserFriends userFriend;

    // 好友头像Label
    private JLabel friendHeadLabel;

    // 好友昵称Label
    private JLabel friendNicknameLabel;

    // 最新聊天记录Label
    private JLabel latestInfoLabel;

    // 最新聊天时间Label
    private JLabel latestTimeLabel;

    // 所有窗体
    private Map<String, JFrame> hahaFrames;

    public FriendPanel(UserFriends userFriend, ImageIcon friendHead, Map<String, JFrame> hahaFrames) {
        this.userFriend = userFriend;
        this.hahaFrames = hahaFrames;

        User friend = userFriend.getFriend();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());
        setSize(359,77);
        setLayout(null);

        friendHeadLabel = new JLabel("");
        friendHeadLabel.setIcon(friendHead);
        friendHeadLabel.setBounds(15, 10, 53, 56);
        add(friendHeadLabel);

        friendNicknameLabel = new JLabel(friend.getNickname());
        friendNicknameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        friendNicknameLabel.setBounds(78, 15, 196, 23);
        add(friendNicknameLabel);

        latestInfoLabel = new JLabel(userFriend.getLatestChartContent());
        latestInfoLabel.setForeground(new Color(174,174,174));
        latestInfoLabel.setFont(new Font("黑体", Font.PLAIN, 14));
        latestInfoLabel.setBounds(78, 45, 271, 19);
        add(latestInfoLabel);

        HAHAFormat hdf = new HAHAFormat();
        String latestChartTime = hdf.latestTimeFormat(userFriend.getLatestChartTime());

        latestTimeLabel = new JLabel(latestChartTime);
        latestTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        latestTimeLabel.setForeground(new Color(139, 139, 139));
        latestTimeLabel.setFont(new Font("黑体", Font.PLAIN, 14));
        latestTimeLabel.setBounds(277, 15, 72, 15);
        add(latestTimeLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(231,231,231));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 双击时出现聊天框loginPort
                if (e.getClickCount() == 2){
                    String friendId = friend.getUserId();
                    ChartHAHA chartHAHA = (ChartHAHA)hahaFrames.get("ChartHAHA" + friendId);
                    UserHAHA userHAHA = (UserHAHA) hahaFrames.get("UserHAHA");
                    if (chartHAHA == null){
                        chartHAHA = ChartHAHA.init(userFriend.getFriendListId(), userFriend.getUser().getUserId(), friend.getUserId(), hahaFrames, userHAHA.getLoginPort());
                        hahaFrames.put("ChartHAHA" + friendId, chartHAHA);
                    }
                }
            }
        });
    }
/*
    public UserFriends getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(UserFriends userFriend) {
        this.userFriend = userFriend;
    }

    public void setHahaFrames(Map<String, JFrame> hahaFrames) {
        this.hahaFrames = hahaFrames;
    }

    *//**
     * 设置好友头像
     * @param newImage
     *//*
    public void setFriendHead(ImageIcon newImage) {
        friendHeadLabel.setIcon(newImage);
    }

    *//**
     * 设置好友头像
     * @param friendHeadImagePath
     *//*
    public void setFriendHead(String friendHeadImagePath) {
        friendHeadLabel.setIcon(new ImageIcon(UserHAHA.class.getResource(friendHeadImagePath)));
    }

    *//**
     * 设置好友昵称
     * @param friendNickname
     *//*
    public void setFriendNickname(String friendNickname) {
        friendNicknameLabel.setText(friendNickname);
    }

    *//**
     * 设置最新记录
     * @param latest
     *//*
    public void setLatestInfo(String latest) {
        latestInfoLabel.setText(latest);
    }

    *//**
     * 设置最新时间
     * @param latestDate
     *//*
    public void setLatestTime(Date latestDate) {
        HAHAFormat hdf = new HAHAFormat();
        String latestChartTime = hdf.latestTimeFormat(latestDate);

        latestTimeLabel.setText(latestChartTime);
    }*/
}
