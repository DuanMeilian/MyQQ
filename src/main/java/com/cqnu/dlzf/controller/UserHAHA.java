package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.bean.UserFriends;
import com.cqnu.dlzf.service.UserFriendsService;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserFriendsServiceImpl;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.socket.UploadHeadServer;
import com.cqnu.dlzf.utils.*;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.components.FriendPanel;
import com.cqnu.dlzf.utils.factory.ImageHandlerFactory;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHAHA extends JFrame {

    private int loginPort;

    private UserService userService = new UserServiceImpl();
    private UserFriendsService userFriendsService = new UserFriendsServiceImpl();

    private DropShadowPanel contentPane;
    private JScrollPane main_scrollJScrollPane;
    private JTextField signText;
    private JLabel nickname_text;
    private JPanel main_list_panel;
    private JPanel user_head_panel;
    private JLabel head_img;

    private Map<String, JFrame> hahaFrames = new HashMap<>();
    private Map<String, FriendPanel> friendPanels;

    public static void init(User loginUser){
        UserHAHA frame = new UserHAHA(loginUser);
        frame.hahaFrames.put("UserHAHA", frame);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        tool.setTextField(frame.signText);
        tool.setSignTextColor(new Color(204, 49, 110));
        tool.setLoginUser(loginUser);
        frame.setVisible(true);
    }

    public UserHAHA(User loginUser){
        HAHAThreadPool.execute(new UploadHeadServer(9888));

        loginPort = InternetUtils.getPort();
        userService.loginSucceed(loginUser.getUserId(), loginPort);

        // 监听用户关闭窗口时就离线
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // 离线
                userService.offline(loginUser.getUsername());
                InternetUtils.reducePort();
                System.exit(0);
            }
        });

        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1500,120,371,770);

        contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        user_head_panel = new JPanel();
        user_head_panel.setLayout(null);
        user_head_panel.setBackground(new Color(204, 49, 110));
        user_head_panel.setBounds(6, 6, 359, 138);
        contentPane.add(user_head_panel);

        JPanel bottom_panel = new JPanel();
        bottom_panel.setBounds(6, 719, 359, 45);
        bottom_panel.setBackground(Color.WHITE);
        bottom_panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        contentPane.add(bottom_panel);
        bottom_panel.setLayout(null);

        JLabel add_friend_button = new JLabel("");
        add_friend_button.setIcon(new ImageIcon(UserHAHA.class.getResource("/img/user/add.png")));
        add_friend_button.setBounds(314, 5, 35, 35);
        add_friend_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddFriendsHAHA addFriendsHAHA = (AddFriendsHAHA)hahaFrames.get("AddFriendsHAHA");
                if (addFriendsHAHA == null){
                    addFriendsHAHA = AddFriendsHAHA.init(loginUser.getUserId(), hahaFrames);
                }
            }
        });
        bottom_panel.add(add_friend_button);

        JLabel close = new JLabel("X");
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        close.setForeground(Color.WHITE);
        close.setBounds(321, 0, 38, 40);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 离线
                userService.offline(loginUser.getUsername());

                InternetUtils.reducePort();

                // 关闭所有窗口
                System.exit(0);
            }

            // 移入背景变为橙色
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setOpaque(true);
                close.setBackground(new Color(254,83,56));
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                close.setBackground(new Color(204, 49, 110));
            }
        });
        user_head_panel.add(close);

        JLabel minimize = new JLabel("一");
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setForeground(Color.WHITE);
        minimize.setFont(new Font("等线", Font.BOLD, 20));
        minimize.setBounds(283, 0, 38, 40);
        minimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 最小化
                setExtendedState(JFrame.ICONIFIED);
            }

            // 移入变为浅色
            @Override
            public void mouseEntered(MouseEvent e) {
                minimize.setOpaque(true);
                minimize.setBackground(new Color(211,68,124));
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                minimize.setBackground(new Color(204, 49, 110));
            }
        });
        user_head_panel.add(minimize);

        head_img = new JLabel("");
        head_img.setBounds(20, 48, 73, 73);
        head_img.setBorder(BorderFactory.createLineBorder(new Color(204, 49, 110), 1));
        user_head_panel.add(head_img);

        head_img.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                head_img.setBorder(BorderFactory.createLineBorder(new Color(235,232,232), 1));
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//                head_img.setBorder(BorderFactory.createLineBorder(new Color(204, 49, 110), 1));
//            }

            // 点击头像进入用户信息界面
            @Override
            public void mouseClicked(MouseEvent e) {
                UserInfoHAHA userInfoHAHA = (UserInfoHAHA)hahaFrames.get("UserInfoHAHA");
                if (userInfoHAHA == null){
                    hahaFrames.put("UserInfoHAHA",UserInfoHAHA.init(loginUser,hahaFrames));
                }
            }
        });

        ImageIcon headImg = ImageHandlerFactory.getResizedImageIcon(loginUser.getImage(), 73);
        head_img.setIcon(headImg);
//        JOptionPane.showMessageDialog(this, UserHAHA.class.getResource("/img/user/add.png"), "正常的图片路径",
//                JOptionPane.INFORMATION_MESSAGE);
//        JOptionPane.showMessageDialog(this, UserHAHA.class.getResource(loginUser.getImage()).toString(), "缩放的图片路径",
//                JOptionPane.ERROR_MESSAGE);

        JLabel top_logo = new JLabel("");
        top_logo.setIcon(new ImageIcon(UserHAHA.class.getResource("/img/user/logo.png")));
        top_logo.setBounds(8, 8, 30, 30);
        user_head_panel.add(top_logo);

        nickname_text = new JLabel(loginUser.getNickname());
        nickname_text.setFont(new Font("黑体", Font.BOLD, 20));
        nickname_text.setForeground(Color.WHITE);
        nickname_text.setBounds(110, 53, 207, 26);
        user_head_panel.add(nickname_text);

        String sign = (loginUser.getSign() == null || "".equals(loginUser.getSign())) ?
                "编辑个性签名" : loginUser.getSign();
        signText = new JTextField(sign);

        // 移出焦点，保存签名的编辑
        signText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String signContent = signText.getText();
                if (!"".equals(signContent)){
                    UserInfoHAHA userInfoHAHA = (UserInfoHAHA) hahaFrames.get("UserInfoHAHA");
                    if (userInfoHAHA != null){
                        userInfoHAHA.refreshSign(signContent);
                    }

                    User updateUser = new User();
                    updateUser.setUsername(loginUser.getUsername());
                    updateUser.setSign(signContent);

                    userService.updateUserSign(updateUser);
                }
            }
        });
        signText.addMouseListener(new MouseAdapter() {
            // 鼠标移入边框变黑
            @Override
            public void mouseEntered(MouseEvent e) {
                signText.setBorder(BorderFactory.createLineBorder(new Color(94,94,94), 1));
            }

            // 鼠标移出边框变回来
            @Override
            public void mouseExited(MouseEvent e) {
                signText.setBorder(BorderFactory.createLineBorder(user_head_panel.getBackground(), 1));
            }

            // 点击出现白色输入框
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("编辑个性签名".equals(signText.getText())) {
                    signText.setText("");
                }
                signText.setForeground(Color.BLACK);
                signText.setBackground(Color.WHITE);
                signText.setCaretColor(Color.BLACK);
            }
        });

        // 隐藏光标（设置光标和背景色相同）
        signText.setCaretColor(user_head_panel.getBackground());
        signText.setBackground(new Color(204, 49, 110));
        signText.setHorizontalAlignment(SwingConstants.LEFT);
        signText.setForeground(Color.WHITE);
        signText.setFont(new Font("黑体", Font.PLAIN, 14));
        signText.setBorder(BorderFactory.createLineBorder(user_head_panel.getBackground(), 1));
        signText.setBounds(110, 89, 208, 26);
        user_head_panel.add(signText);

        main_list_panel = new JPanel();
        main_list_panel.setBackground(Color.WHITE);
        main_list_panel.setLayout(null);

        main_scrollJScrollPane = new JScrollPane(main_list_panel);
        main_scrollJScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        main_scrollJScrollPane.setBounds(6, 144, 359, 576);
        main_scrollJScrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(main_scrollJScrollPane);

        renderFrame(loginUser.getUserId());
    }

    /**
     * 实时刷新用户信息
     * @param userId
     */
    public void renderFrame(String userId){
        HAHAThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    User user = userService.getUserById(userId);

                    ImageIcon headImg = ImageHandlerFactory.getResizedImageIcon(user.getImage(), 73);
                    head_img.setIcon(headImg);

                    main_list_panel.removeAll();
                    List<UserFriends> userFriends = userFriendsService.getUserFriendsByUserId(userId);
                    friendPanels = new HashMap();
                    int i;
                    for (i = 0; i < userFriends.size(); i++){
                        UserFriends userFriend = userFriends.get(i);
                        User friend = userFriend.getFriend();

                        ImageIcon friendHead = getFriendHeadByState(friend.getImage(), friend.getState());
                        FriendPanel friend_panel = new FriendPanel(userFriend, friendHead, hahaFrames);

                        friend_panel.setLocation(0,  i*friend_panel.getHeight());
                        main_list_panel.add(friend_panel);

                        friendPanels.put(userFriend.getFriendListId(),friend_panel);
                    }
                    repaint();

                    int y = (i-1)*77;
                    int scrollPaneHeight = main_scrollJScrollPane.getHeight();
                    int contentHeight = y + 77;
                    if (contentHeight > scrollPaneHeight){
                        main_list_panel.setSize(main_scrollJScrollPane.getWidth()-20,contentHeight);
                        //main_list_panel.setPreferredSize(new Dimension(main_scrollJScrollPane.getWidth()-20,contentHeight));
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void refreshUserInfo(User user){
        nickname_text.setText(user.getNickname());
        signText.setText(user.getSign());
    }

    /**
     * 通过状态获取头像
     * @param imgPath
     * @param state 1:上线，彩图；0：下线：黑白图
     * @return
     */
    private ImageIcon getFriendHeadByState(String imgPath, Integer state){
        ImageIcon friendHead = null;
        // 下线
        if (state == 0){
            friendHead = ImageHandlerFactory.changedBWImage(imgPath, 47);
        }
        // 上线
        else if (state == 1){
            friendHead = ImageHandlerFactory.getResizedImageIcon(imgPath, 47);
        }
        return friendHead;
    }

    /**
     * 修改UserHAHA界面的签名
     * @param sign
     */
    public void setSignText(String sign){
        signText.setText(sign);
    }

    /**
     * 获取登录端口号
     * @return
     */
    public int getLoginPort() {
        return loginPort;
    }
}