package com.cqnu.dlzf.utils.components;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;
import com.cqnu.dlzf.service.UserFriendsService;
import com.cqnu.dlzf.service.impl.UserFriendsServiceImpl;
import com.cqnu.dlzf.utils.HAHAFormat;
import com.cqnu.dlzf.utils.factory.ImageHandlerFactory;
import com.cqnu.dlzf.utils.MyBatisUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class AddUserPanel extends JPanel{

    // 阻止重复添加
    private boolean isAdded = false;

    private UserFriendsService userFriendsService = new UserFriendsServiceImpl();

    public AddUserPanel(String ownerId, User searchUser){
        setBackground(Color.WHITE);
        setSize(293,120);
        setLayout(null);


        ImageIcon headImage = ImageHandlerFactory.getResizedImageIcon(searchUser.getImage(), 71);
        JLabel head_icon = new JLabel("");
        head_icon.setIcon(headImage);
        head_icon.setBounds(20, 24, headImage.getIconWidth(), headImage.getIconHeight());
        add(head_icon);

        JLabel nickname_text = new JLabel(searchUser.getNickname());
        nickname_text.setHorizontalAlignment(SwingConstants.LEFT);
        nickname_text.setFont(new Font("黑体", Font.PLAIN, 18));
        nickname_text.setBounds(110, 24, 160, 20);
        add(nickname_text);

        HAHAFormat hahaFormat = new HAHAFormat();
        int age = hahaFormat.getAgeByBirthday(searchUser.getBirthday());
        if (age != -1){
            JLabel age_text = new JLabel(age + "岁");
            age_text.setForeground(Color.GRAY);
            age_text.setFont(new Font("黑体", Font.PLAIN, 14));
            age_text.setBounds(110, 50, 58, 15);
            add(age_text);
        }

        JLabel add_button = new JLabel(" ＋ 好友");
        add_button.setOpaque(true);
        add_button.setBackground(new Color(202,115,149));
        add_button.setForeground(Color.WHITE);
        add_button.setFont(new Font("黑体", Font.PLAIN, 14));
        add_button.setBounds(110, 71, 65, 24);
        add(add_button);

        if (userFriendsService.isFriend(ownerId, searchUser.getUserId())){
            add_button.setBackground(Color.GRAY);
            return;
        }

        MouseAdapter changeBGColor = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add_button.setBackground(new Color(201,67,119));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add_button.setBackground(new Color(202,115,149));
            }
        };
        add_button.addMouseListener(changeBGColor);

        MouseAdapter addFriend = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isAdded){
                    isAdded = true;
                    UserFriends userFriend = new UserFriends();
                    userFriend.setFriendListId(MyBatisUtils.getUUID());
                    userFriend.setUser(new User(ownerId));
                    userFriend.setFriend(new User(searchUser.getUserId()));
                    userFriend.setLatestChartContent("我们已经是好友了，现在开始对话吧！");
                    userFriend.setLatestChartTime(new Date(System.currentTimeMillis()));

                    int rows = userFriendsService.addFriend(userFriend);
                    if (rows == 1){
                        add_button.setBackground(Color.GRAY);
                        add_button.removeMouseListener(changeBGColor);
                        add_button.removeMouseListener(this);
                    }
                    isAdded = false;
                }
            }
        };
        add_button.addMouseListener(addFriend);
    }
}
