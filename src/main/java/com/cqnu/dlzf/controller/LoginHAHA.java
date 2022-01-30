package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.initdb.HAHADB;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.components.LoginTipDialog;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class LoginHAHA extends JFrame {

    private UserService userService = new UserServiceImpl();

    private LoginTipDialog tipDialog;
    private JPanel contentPane;
    private JTextField account_text;
    private JPasswordField passwordField;

    // 避免重复登录
    private boolean isClicked = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.setProperty("sun.java2d.uiScale", "1.0");
                try {
                    init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static LoginHAHA init(){
        LoginHAHA frame = new LoginHAHA();
        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);

        tool.setTipDialog(frame.tipDialog);
        frame.setVisible(true);
        return frame;
    }

    public LoginTipDialog getLoginTipDialog(){
        return tipDialog;
    }

    /**
     * Create the frame.
     */
    public LoginHAHA() {
//        JOptionPane.showMessageDialog(this, LoginHAHA.class.getResource("/head_uploads/") + "dasdsa.png",
//                "路径",JOptionPane.INFORMATION_MESSAGE);

        tipDialog = new LoginTipDialog("");
        tipDialog.setLocationRelativeTo(this);
        int y = tipDialog.getY();
        tipDialog.setLocation(tipDialog.getX(), y + 190);

        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 536, 412);
        setLocationRelativeTo(null);

        contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(6, 6, 524, 158);
        panel.setBackground(new Color(204, 49, 110));
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(6, 164, 524, 242);
        main_panel.setLayout(null);
        contentPane.add(main_panel);

        UsualJLabelTool tool = new UsualJLabelTool(this, panel.getWidth(), 45);
        JLabel close = tool.getCloseJLable(panel.getBackground(), new Color(254,84,57));
        panel.add(close);

        JLabel minimize = tool.getMinimizeJLabel(panel.getBackground(), new Color(241,75,140));
        panel.add(minimize);

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginHAHA.class.getResource("/img/logo1.png")));
        //成：logo.setIcon(new ImageIcon(LoginHAHA.class.getResource("/head_uploads/f5b7ba23a00b4e34b624c817a8a83faa.png")));
        logo.setBounds(22, 34, 111, 94);
        panel.add(logo);

        JLabel qqName = new JLabel("HAHA");
        qqName.setFont(new Font("Bahnschrift", Font.PLAIN, 38));
        qqName.setForeground(Color.WHITE);
        qqName.setBounds(143, 63, 120, 52);
        panel.add(qqName);

        JLabel toRegister = new JLabel("注册账号");
        toRegister.setForeground(new Color(179,179,179));
        toRegister.setFont(new Font("宋体", Font.PLAIN, 16));
        toRegister.setBounds(438, 196, 64, 28);
        toRegister.addMouseListener(new MouseAdapter() {

            // 移进变成粉色
            @Override
            public void mouseEntered(MouseEvent e) {
                toRegister.setForeground(new Color(204, 49, 110));
            }

            // 移进变回灰色
            @Override
            public void mouseExited(MouseEvent e) {
                toRegister.setForeground(new Color(179,179,179));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开登录窗口
                RegisterHAHA frame;
                try {
                    RegisterHAHA.init();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // 关闭注册窗口
                setVisible(false);
                dispose();

                // 关闭提示信息窗口
                tipDialog.dispose();
            }
        });
        main_panel.add(toRegister);

        account_text = new JTextField("10000");
        account_text.setHorizontalAlignment(SwingConstants.LEFT);
        account_text.setFont(new Font("宋体", Font.BOLD, 26));
        account_text.setBounds(136, 30, 281, 39);
        account_text.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        account_text.setColumns(10);
        account_text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tipDialog.setVisible(false);
            }
        });
        main_panel.add(account_text);

        JLabel account_name = new JLabel("");
        account_name.setIcon(new ImageIcon(LoginHAHA.class.getResource("/img/account_name.png")));
        account_name.setBounds(105, 42, 19, 17);
        main_panel.add(account_name);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(LoginHAHA.class.getResource("/img/lock.png")));
        lblNewLabel_2.setBounds(105, 99, 19, 23);
        main_panel.add(lblNewLabel_2);

        JLabel login_button = new JLabel("登录");
        login_button.setOpaque(true);
        login_button.setHorizontalAlignment(SwingConstants.CENTER);
        login_button.setFont(new Font("宋体", Font.PLAIN, 20));
        login_button.setForeground(Color.WHITE);
        login_button.setBackground(new Color(204, 49, 110));
        login_button.setBounds(105, 162, 312, 40);
        login_button.addMouseListener(new MouseAdapter() {

            // 移入注册按钮变为深色
            @Override
            public void mouseEntered(MouseEvent e) {
                login_button.setBackground(new Color(188,46,88));
                login_button.setBorder(BorderFactory.createLineBorder(new Color(188,46,88)));
            }

            // 移出变回原色
            @Override
            public void mouseExited(MouseEvent e) {
                login_button.setBackground(new Color(204, 49, 110));
                login_button.setBorder(BorderFactory.createLineBorder(new Color(204, 49, 110)));
            }

            // 实现登录
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isClicked){
                    isClicked = true;

                    // 避免长停于按钮
                    HAHAThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            if("".equals(account_text.getText()) || "".equals(passwordField.getText())){
                                tipDialog.setContent("请输入完整信息。");
                                tipDialog.setVisible(true);
                                isClicked=false;
                                return;
                            }

                            User loginUser = userService.login(account_text.getText(), passwordField.getText());
                            // 用户存在
                            if (loginUser != null){
                                // 用户已在线
                                if (loginUser.getState() == 1){
                                    tipDialog.setContent("已在HAHA登录了" + account_text.getText() + "，不能重复登录。");
                                    tipDialog.setVisible(true);
                                }
                                // 登录成功
                                else{
                                    dispose();
                                    loginUser.setState(1);
                                    UserHAHA.init(loginUser);
                                }
                            }
                            // 用户不存在
                            else{
                                tipDialog.setContent("该用户不存在，请检查用户名或密码。");
                                tipDialog.setVisible(true);
                            }

                            isClicked=false;
                        }
                    });
                }
            }
        });
        main_panel.add(login_button);

        passwordField = new JPasswordField("123");
        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        passwordField.setFont(new Font("宋体", Font.BOLD, 26));
        passwordField.setColumns(10);
        passwordField.setBounds(136, 91, 281, 39);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220,220,220)));
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                tipDialog.setVisible(false);
            }
        });
        main_panel.add(passwordField);

        KeyAdapter enterLogin = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 回车事件
                if((char)e.getKeyChar() == KeyEvent.VK_ENTER && !isClicked) {
                    isClicked = true;

                    // 避免长停于按钮
                    HAHAThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            if("".equals(account_text.getText()) || "".equals(passwordField.getText())){
                                tipDialog.setContent("请输入完整信息。");
                                tipDialog.setVisible(true);
                                isClicked=false;
                                return;
                            }

                            User loginUser = userService.login(account_text.getText(), passwordField.getText());
                            // 用户存在
                            if (loginUser != null){
                                // 用户已在线
                                if (loginUser.getState() == 1){
                                    tipDialog.setContent("已在HAHA登录了" + account_text.getText() + "，不能重复登录。");
                                    tipDialog.setVisible(true);
                                }
                                // 登录成功
                                else{
                                    dispose();
                                    loginUser.setState(1);
                                    UserHAHA.init(loginUser);
                                }
                            }
                            // 用户不存在
                            else{
                                tipDialog.setContent("该用户不存在，请检查用户名或密码。");
                                tipDialog.setVisible(true);
                            }

                            isClicked=false;
                        }
                    });
                }
            }
        };

        account_text.addKeyListener(enterLogin);
        passwordField.addKeyListener(enterLogin);

        JLabel initDB_button = new JLabel("初始化数据库");
        initDB_button.setForeground(new Color(179, 179, 179));
        initDB_button.setFont(new Font("黑体", Font.PLAIN, 14));
        initDB_button.setBounds(8, 215, 86, 22);
        UsualJLabelTool.addMouseEnteredAndExitedCF(initDB_button,new Color(179, 179, 179),Color.DARK_GRAY);
        LoginHAHA temp = this;
        initDB_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    HAHADB.init();
                    JOptionPane.showMessageDialog(temp, "初始化成功","初始化数据库", JOptionPane.PLAIN_MESSAGE);
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        main_panel.add(initDB_button);
    }
}
