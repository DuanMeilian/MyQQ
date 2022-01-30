package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.factory.ImageHandlerFactory;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.HAHAFormat;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.util.Map;

public class UserInfoHAHA extends JFrame {

    private DropShadowPanel contentPane;

    private Map<String, JFrame> hahaFrames;

    private JTextField sign_text;
    private JLabel nickname_text;
    private JLabel company_text;
    private JLabel telephone_text;
    private JLabel info_text;
    private JLabel company_icon;
    private JLabel telephone_icon;
    private JLabel info_icon;
    private JLabel user_head_img;

    private UserService userService = new UserServiceImpl();

    public static UserInfoHAHA init(User loginUser, Map<String, JFrame> hahaFrames) {
        UserInfoHAHA frame = new UserInfoHAHA(loginUser);
        frame.hahaFrames = hahaFrames;
        frame.hahaFrames.put("UserInfoHAHA",frame);
        frame.setVisible(true);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        tool.setUserInfoHAHA(frame);
        tool.setTextField(frame.sign_text);
        tool.setLoginUser(loginUser);
        tool.setUserHAHA((UserHAHA)hahaFrames.get("UserHAHA"));
        tool.setSignTextColor(new Color(91,119,172));
        return frame;
    }

    public Map<String, JFrame> getHahaFrames() {
        return hahaFrames;
    }

    public JTextField getSignText() {
        return sign_text;
    }

    /**
     * Create the frame.
     */
    public UserInfoHAHA(User loginUser) {
        setBounds(100, 100, 914, 462);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel left_cover = new JLabel("");
        left_cover.setIcon(new ImageIcon(UserInfoHAHA.class.getResource("/img/user/default_cover.png")));
        left_cover.setBounds(6, 6, 451, 450);
        contentPane.add(left_cover);

        JPanel right_panel = new JPanel();
        right_panel.setBounds(457, 6, 451, 450);
        contentPane.add(right_panel);
        right_panel.setLayout(null);

        JPanel user_info_panel = new JPanel();
        user_info_panel.setBackground(new Color(91,119,172));
        user_info_panel.setBounds(0, 0, 451, 150);
        right_panel.add(user_info_panel);
        user_info_panel.setLayout(null);

        user_head_img = new JLabel("");
        user_head_img.setBorder(BorderFactory.createLineBorder(new Color(91,119,172), 4));
        user_head_img.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                user_head_img.setBorder(BorderFactory.createLineBorder(new Color(150,182,228), 4));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                user_head_img.setBorder(BorderFactory.createLineBorder(new Color(91,119,172), 4));
//            }

            @Override
            public void mouseClicked(MouseEvent e) {
                UploadHeadHAHA uploadHeadHAHA = (UploadHeadHAHA) hahaFrames.get("UploadHeadHAHA");
                if (uploadHeadHAHA == null){
                    uploadHeadHAHA = UploadHeadHAHA.init(loginUser.getUserId(),hahaFrames);
                }
            }
        });

        ImageIcon headImg = ImageHandlerFactory.getResizedImageIcon(loginUser.getImage(), 110);
        user_head_img.setIcon(headImg);
        user_head_img.setBounds(20, 25, 110, 110);
        user_info_panel.add(user_head_img);

        nickname_text = new JLabel(loginUser.getNickname());
        nickname_text.setForeground(Color.WHITE);
        nickname_text.setFont(new Font("黑体", Font.PLAIN, 28));
        nickname_text.setBounds(140, 50, 282, 32);
        user_info_panel.add(nickname_text);

        String sign = (loginUser.getSign() == null || "".equals(loginUser.getSign())) ?
                "编辑个性签名" : loginUser.getSign();
        sign_text = new JTextField(sign);
        sign_text.setFont(new Font("黑体", Font.PLAIN, 16));
        sign_text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sign_text.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sign_text.setBorder(BorderFactory.createLineBorder(user_info_panel.getBackground(), 1));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if ("编辑个性签名".equals(sign_text.getText())) {
                    sign_text.setText("");
                }
                sign_text.setForeground(Color.BLACK);
                sign_text.setBackground(Color.WHITE);
                sign_text.setCaretColor(Color.BLACK);
            }
        });

        sign_text.setBackground(user_info_panel.getBackground());
        // 隐藏光标（设置光标和背景色相同）
        sign_text.setCaretColor(user_info_panel.getBackground());
        sign_text.setHorizontalAlignment(SwingConstants.LEFT);
        sign_text.setForeground(Color.WHITE);
        sign_text.setBorder(BorderFactory.createLineBorder(user_info_panel.getBackground(), 1));
        sign_text.setBounds(140, 92, 282, 26);
        user_info_panel.add(sign_text);

        UsualJLabelTool usualJLabelTool = new UsualJLabelTool(this,user_info_panel.getWidth(),35);
        JLabel minimize =
                usualJLabelTool.getMinimizeJLabel(user_info_panel.getBackground(), new Color(110,136,185));
        minimize.setFont(new Font("等线", Font.PLAIN, 18));
        user_info_panel.add(minimize);

        JLabel close = usualJLabelTool.getCloseJLable(user_info_panel.getBackground(), new Color(254,83,56));
        user_info_panel.add(close);

        JPanel user_detail_panel = new JPanel();
        user_detail_panel.setBackground(Color.WHITE);
        user_detail_panel.setBounds(0, 150, 451, 300);
        right_panel.add(user_detail_panel);
        user_detail_panel.setLayout(null);

        JLabel account_icon = new JLabel("");
        account_icon.setIcon(new ImageIcon(UserInfoHAHA.class.getResource("/img/info/bear_head.png")));
        account_icon.setBounds(51, 28, 26, 23);
        user_detail_panel.add(account_icon);

        JLabel account_text = new JLabel(loginUser.getUsername());
        account_text.setFont(new Font("黑体", Font.PLAIN, 22));
        account_text.setBounds(97, 28, 165, 23);
        user_detail_panel.add(account_text);

        JLabel toEdit = new JLabel("编辑资料");
        UsualJLabelTool.addMouseEnteredAndExitedCF(toEdit, new Color(79,106,156), new Color(151,168,200));
        toEdit.setForeground(new Color(79,106,156));
        toEdit.setHorizontalAlignment(SwingConstants.CENTER);
        toEdit.setFont(new Font("宋体", Font.PLAIN, 18));
        toEdit.setBounds(337, 26, 86, 23);
        user_detail_panel.add(toEdit);

        toEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditUserHAHA editUserHAHA = (EditUserHAHA) hahaFrames.get("EditUserHAHA");
                if (editUserHAHA == null){
                    editUserHAHA = EditUserHAHA.init(loginUser,hahaFrames);
                }
            }
        });

        // 用户信息：性别 年龄 生日 星座 生肖
        String info = new HAHAFormat().userInfoFormat(loginUser.getGender(), loginUser.getBirthday());
        // 用户信息有内容
        info_icon = new JLabel("");
        info_icon.setIcon(new ImageIcon(UserInfoHAHA.class.getResource("/img/info/info.png")));
        info_icon.setBounds(51, 97, 26, 25);
        user_detail_panel.add(info_icon);

        info_text = new JLabel(info == null ? "" : info);
        info_text.setFont(new Font("黑体", Font.PLAIN, 17));
        info_text.setBounds(97, 97, 326, 25);
        user_detail_panel.add(info_text);

        if ("".equals(info) || info == null){
            info_icon.setVisible(false);
        }

        String telephone = loginUser.getTelephone();
        telephone_icon = new JLabel("");
        telephone_icon.setIcon(new ImageIcon(UserInfoHAHA.class.getResource("/img/info/telephone.png")));
        telephone_icon.setBounds(51, 172, 26, 25);
        user_detail_panel.add(telephone_icon);

        telephone_text = new JLabel(telephone == null ? "" : telephone);
        telephone_text.setFont(new Font("黑体", Font.PLAIN, 20));
        telephone_text.setBounds(97, 172, 125, 25);
        user_detail_panel.add(telephone_text);

        if ("".equals(telephone) || telephone == null){
            telephone_icon.setVisible(false);
        }

        String company = loginUser.getCompany();
        company_icon = new JLabel("");
        company_icon.setIcon(new ImageIcon(UserInfoHAHA.class.getResource("/img/info/company.png")));
        company_icon.setBounds(51, 247, 26, 25);
        user_detail_panel.add(company_icon);

        company_text = new JLabel(("".equals(company) || company == null) ? "" : company);
        company_text.setFont(new Font("黑体", Font.PLAIN, 20));
        company_text.setBounds(97, 247, 125, 25);
        user_detail_panel.add(company_text);

        if ("".equals(company) || company == null){
            company_icon.setVisible(false);
        }

        render(loginUser);
    }

    /**
     * 实时刷新
     * @param loginUser
     */
    public void render(User loginUser){
        HAHAThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    User newUser = userService.getUserById(loginUser.getUserId());
                    refreshFrame(newUser);
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
     * 刷新用户信息界面UserInfoHAHA
     * @param newUser
     */
    public void refreshFrame(User newUser){
        ImageIcon headImg = ImageHandlerFactory.getResizedImageIcon(newUser.getImage(), 110);
        user_head_img.setIcon(headImg);

        String info = new HAHAFormat().userInfoFormat(newUser.getGender(), newUser.getBirthday());
        refreshText(info, info_icon, info_text);
        refreshText(newUser.getTelephone(), telephone_icon, telephone_text);
        refreshText(newUser.getCompany(), company_icon, company_text);
        nickname_text.setText(newUser.getNickname());
        //sign_text.setText(newUser.getSign());
    }

    public void refreshSign(String newSign){
        sign_text.setText(newSign);
    }


    /**
     * 刷新信息栏
     * @param info
     * @param icon
     * @param text
     */
    private void refreshText(String info, JLabel icon, JLabel text){
        if (info != null && !"".equals(info)){
            icon.setVisible(true);
            text.setText(info);
            return;
        }
        icon.setVisible(false);
        text.setText("");
    }
}
