package com.cqnu.dlzf.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.enums.Owner;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.factory.TipFactory;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

public class RegisterHAHA extends JFrame {

    private UserService userService = new UserServiceImpl();

    private DropShadowPanel contentPane;
    private JTextField nickname;
    private JPasswordField passwordField;
    private JPasswordField ensure_password_field;
    private JLabel password_tip;
    private JLabel ensure_tip;

    // 避免点击一次注册后重复点击，导致数据库中出现多个同名用户
    private boolean isClicked = false;

    public static RegisterHAHA init() throws Exception {
        RegisterHAHA frame = new RegisterHAHA();
        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Create the frame.
     * @throws Exception
     */
    public RegisterHAHA() throws Exception {
        getContentPane().setLayout(null);
        setUndecorated(true);   // 无边框
        setBackground(new Color(0,0,0,0));  // 背景为透明色
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 536, 412);
        setLocationRelativeTo(null);

        contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(6, 6, 524, 156);
        panel.setBackground(new Color(204, 49, 110));
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.WHITE);
        main_panel.setLayout(null);
        main_panel.setBounds(6, 162, 524, 244);
        contentPane.add(main_panel);

        UsualJLabelTool tool = new UsualJLabelTool(this, panel.getWidth(), 45);
        JLabel close = tool.getCloseJLable(panel.getBackground(), new Color(254,84,57));
        panel.add(close);

        JLabel minimize = tool.getMinimizeJLabel(panel.getBackground(), new Color(241,75,140));
        panel.add(minimize);

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginHAHA.class.getResource("/img/logo1.png")));
        logo.setBounds(22, 34, 111, 94);
        panel.add(logo);

        JLabel qqName = new JLabel("HAHA");
        qqName.setFont(new Font("Bahnschrift", Font.PLAIN, 38));
        qqName.setForeground(Color.WHITE);
        qqName.setBounds(143, 63, 120, 52);
        panel.add(qqName);

        JLabel toLogin = new JLabel("登录账户");
        toLogin.addMouseListener(new MouseAdapter() {

            // 移进变成粉色
            @Override
            public void mouseEntered(MouseEvent e) {
                toLogin.setForeground(new Color(204, 49, 110));
            }

            // 移进变回灰色
            @Override
            public void mouseExited(MouseEvent e) {
                toLogin.setForeground(new Color(179,179,179));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开登录窗口
                LoginHAHA.init();

                // 关闭注册窗口
                dispose();
            }
        });

        toLogin.setForeground(new Color(179,179,179));
        toLogin.setFont(new Font("宋体", Font.PLAIN, 16));
        toLogin.setBounds(440, 208, 64, 19);
        main_panel.add(toLogin);

        JLabel account_name = new JLabel("");
        account_name.setIcon(new ImageIcon(RegisterHAHA.class.getResource("/img/nickname.png")));
        account_name.setBounds(151, 31, 19, 20);
        main_panel.add(account_name);

        JLabel register_button = new JLabel("注册");
        register_button.addMouseListener(new MouseAdapter() {

            // 移入注册按钮变为深色
            @Override
            public void mouseEntered(MouseEvent e) {
                register_button.setBackground(new Color(188,46,88));
                register_button.setBorder(BorderFactory.createLineBorder(new Color(188,46,88)));
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                register_button.setBackground(new Color(204, 49, 110));
                register_button.setBorder(BorderFactory.createLineBorder(new Color(204, 49, 110)));
            }

            // 注册检验
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!password_tip.isVisible() && !ensure_tip.isVisible() && !"请输入昵称...".equals(nickname.getText()) && !isClicked) {
                    isClicked = true;

                    HAHAThreadPool.execute(new Runnable() {

                        @Override
                        public void run() {
                            // 实行注册
                            User user = new User();
                            user.setNickname(nickname.getText());
                            user.setPassword(passwordField.getText());

                            String username = userService.register(user);
                            if (username != null){
                                JOptionPane.showMessageDialog(getParent(), "用户名为【" + username + "】", "用户名",
                                        JOptionPane.INFORMATION_MESSAGE, null);

                                // 打开登录窗口
                                LoginHAHA.init();

                                dispose();
                            }
                            else{
                                JOptionPane.showMessageDialog(getParent(), "注册失败", "注册",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                }
            }
        });
        register_button.setOpaque(true);
        register_button.setHorizontalAlignment(SwingConstants.CENTER);
        register_button.setFont(new Font("宋体", Font.PLAIN, 20));
        register_button.setForeground(Color.WHITE);
        register_button.setBackground(new Color(204, 49, 110));
        register_button.setBounds(151, 179, 217, 39);
        // 去掉按钮文字周围的虚线框
        //register_button.setFocusPainted(false);
        main_panel.add(register_button);

        JLabel register_pass = new JLabel("");
        register_pass.setIcon(new ImageIcon(RegisterHAHA.class.getResource("/img/lock.png")));
        register_pass.setBounds(151, 80, 19, 23);
        main_panel.add(register_pass);

        JLabel register_ensure_pass = new JLabel("");
        register_ensure_pass.setIcon(new ImageIcon(RegisterHAHA.class.getResource("/img/lock.png")));
        register_ensure_pass.setBounds(151, 128, 19, 23);
        main_panel.add(register_ensure_pass);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("宋体", Font.PLAIN, 20));
        passwordField.addFocusListener(new FocusAdapter() {

            // 移出密码输入框，如果密码为空，显示提示
            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(passwordField.getText())) {
                    password_tip.setVisible(true);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                password_tip.setVisible(false);
            }
        });

        // 打开窗口时，焦点在这个输入框上
//        addWindowListener(new WindowAdapter() {
//            public void windowOpened(WindowEvent e) {
//                passwordField.requestFocus();
//            }
//        });
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setColumns(10);
        passwordField.setBounds(180, 74, 188, 30);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        main_panel.add(passwordField);

        ensure_password_field = new JPasswordField();
        ensure_password_field.setFont(new Font("宋体", Font.PLAIN, 20));
        ensure_password_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!ensure_password_field.getText().equals(passwordField.getText())) {
                    ensure_tip.setVisible(true);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                ensure_tip.setVisible(false);
            }
        });
        ensure_password_field.setHorizontalAlignment(SwingConstants.LEFT);
        ensure_password_field.setColumns(10);
        ensure_password_field.setBounds(180, 124, 188, 30);
        ensure_password_field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        main_panel.add(ensure_password_field);

        nickname = new JTextField("请输入昵称...");
//        nickname.addFocusListener(new FocusAdapter() {
//            // 移出后如果昵称为空串，显示提示
//            @Override
//            public void focusLost(FocusEvent e) {
//                if ("".equals(nickname.getText())) {
//                    nickname.setText("请输入昵称...");
//                }
//            }
//
//            @Override
//            public void focusGained(FocusEvent e) {
//                if("请输入昵称...".equals(nickname.getText())) {
//                    nickname.setText("");
//                }
//            }
//        });
        nickname.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if("请输入昵称...".equals(nickname.getText())) {
                    nickname.setCaretColor(Color.BLACK);
                    nickname.setText("");
                }
            }
        });
        nickname.setCaretColor(Color.WHITE);
        nickname.setFont(new Font("宋体", Font.PLAIN, 20));
        nickname.setHorizontalAlignment(SwingConstants.LEFT);
        nickname.setBounds(180, 24, 188, 30);
        nickname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        nickname.setColumns(10);
        main_panel.add(nickname);

        password_tip = TipFactory.createTip("输入密码", 180, 108, 58, 15);
        main_panel.add(password_tip);

        ensure_tip = TipFactory.createTip("确认密码与上次相同", 180, 158, 121, 15);
        main_panel.add(ensure_tip);
    }
}