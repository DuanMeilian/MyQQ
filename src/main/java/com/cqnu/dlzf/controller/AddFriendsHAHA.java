package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.dao.UserMapper;
import com.cqnu.dlzf.service.UserFriendsService;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserFriendsServiceImpl;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.components.AddUserPanel;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class AddFriendsHAHA extends JFrame {

    private Map<String, JFrame> hahaFrames;
    private String userId;

    private JPanel init_panel;
    private JTextField search_field;
    private JPanel result_panel;
    private JPanel results_panel;
    private JLabel noresults_text;
    private JLabel info_text;

    private UserService userService = new UserServiceImpl();

    public static AddFriendsHAHA init(String userId, Map<String, JFrame> hahaFrames) {
        AddFriendsHAHA frame = new AddFriendsHAHA(userId);
        frame.hahaFrames = hahaFrames;
        frame.hahaFrames.put("AddFriendsHAHA",frame);
        frame.setVisible(true);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        return frame;
    }

    public AddFriendsHAHA(String userId) {
        createContents(userId);
    }

    private void createContents(String userId) {
        this.userId = userId;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 892, 753);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);

        JPanel contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel top_panel = new JPanel();
        top_panel.setBackground(new Color(203,49,109));
        top_panel.setBounds(6, 6, getWidth()-12, 60);
        contentPane.add(top_panel);
        top_panel.setLayout(null);

        UsualJLabelTool tool = new UsualJLabelTool(this, top_panel.getWidth(), 35);
        JLabel close = tool.getCloseJLable(top_panel.getBackground(), new Color(254,84,57));
        top_panel.add(close);

        JLabel minimize = tool.getMinimizeJLabel(top_panel.getBackground(), new Color(241,75,140));
        top_panel.add(minimize);

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(AddFriendsHAHA.class.getResource("/img/user/logo.png")));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setBounds(0, 0, 33, 31);
        top_panel.add(logo);

        JLabel small_title = new JLabel("查找");
        small_title.setForeground(Color.WHITE);
        small_title.setFont(new Font("黑体", Font.PLAIN, 16));
        small_title.setBounds(35, 9, 33, 16);
        top_panel.add(small_title);

        JLabel big_title = new JLabel("找人");
        big_title.setHorizontalAlignment(SwingConstants.CENTER);
        big_title.setForeground(Color.WHITE);
        big_title.setFont(new Font("黑体", Font.PLAIN, 22));
        big_title.setSize(52,52);
        big_title.setLocation((top_panel.getWidth()- big_title.getWidth())/2, (top_panel.getHeight()- big_title.getHeight())/2);
        top_panel.add(big_title);

        JPanel search_panel = new JPanel();
        search_panel.setBackground(new Color(236,241,247));
        search_panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));
        search_panel.setBounds(6, 66, getWidth()-12, 100);
        contentPane.add(search_panel);
        search_panel.setLayout(null);

        search_field = new JTextField(" 请输入HH号码/昵称");
        search_field.setFocusable(false);
        search_field.setForeground(new Color(131,133,134));
        search_field.setFont(new Font("黑体", Font.PLAIN, 16));
        search_field.setBackground(search_panel.getBackground());
        search_field.setBounds(131, 29, 452, 44);
        search_field.setBorder(BorderFactory.createLineBorder(top_panel.getBackground()));
        search_panel.add(search_field);
        search_field.setColumns(10);

        JLabel search_button = new JLabel("查找");
        search_button.setToolTipText("查找");
        search_button.setOpaque(true);
        search_button.setBackground(top_panel.getBackground());
        search_button.setForeground(Color.WHITE);
        search_button.setFont(new Font("黑体", Font.PLAIN, 20));
        search_button.setHorizontalAlignment(SwingConstants.CENTER);
        search_button.setBounds(603, 29, 145, 44);
        search_panel.add(search_button);
        UsualJLabelTool.addMouseEnteredAndExitedCB(search_button, top_panel.getBackground(), new Color(188,45,101));

        init_panel = new JPanel();
        init_panel.setBackground(new Color(236,241,247));
        init_panel.setBounds(6, 166, 880, 581);
        contentPane.add(init_panel);
        init_panel.setLayout(null);

        JLabel friendship_image = new JLabel("");
        friendship_image.setIcon(new ImageIcon(AddFriendsHAHA.class.getResource("/img/add/friendship.png")));
        friendship_image.setBounds(165, 119, 550, 309);
        init_panel.add(friendship_image);

        result_panel = new JPanel();
        result_panel.setBackground(Color.WHITE);
        result_panel.setBounds(6, 166, 880, 581);
        contentPane.add(result_panel);
        result_panel.setLayout(null);
        result_panel.setVisible(false);

        JPanel info_panel = new JPanel();
        info_panel.setBackground(new Color(233,233,233));
        info_panel.setBounds(0, 0, getWidth()-12, 41);
        result_panel.add(info_panel);
        info_panel.setLayout(null);

        JLabel return_button = new JLabel("返回");
        return_button.setForeground(top_panel.getBackground());
        return_button.setHorizontalAlignment(SwingConstants.CENTER);
        return_button.setFont(new Font("黑体", Font.PLAIN, 14));
        return_button.setIcon(new ImageIcon(AddFriendsHAHA.class.getResource("/img/add/return.png")));
        return_button.setBounds(0, 3, 82, 35);
        return_button.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(201,200,200)));
        info_panel.add(return_button);

        info_text = new JLabel("搜索：");
        info_text.setForeground(new Color(131,133,134));
        info_text.setHorizontalAlignment(SwingConstants.LEFT);
        info_text.setFont(new Font("黑体", Font.PLAIN, 14));
        info_text.setBounds(102, 0, 760, 41);
        info_panel.add(info_text);

        results_panel = new JPanel();
        results_panel.setBackground(Color.WHITE);
        results_panel.setBounds(0, 41, 880, 540);
        result_panel.add(results_panel);
        results_panel.setLayout(null);

        noresults_text = new JLabel("没有搜索到相关结果");
        noresults_text.setFont(new Font("黑体", Font.PLAIN, 16));
        noresults_text.setBounds(368, 240, 144, 16);
        results_panel.add(noresults_text);

        search_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (" 请输入HH号码/昵称".equals(search_field.getText())) {
                    search_field.setText("");
                }
                search_field.setFocusable(true);
                search_field.requestFocus();
                search_field.setForeground(new Color(12,12,12));
            }
        });

        search_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findUsers();
            }
        });

        return_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                result_panel.setVisible(false);
                init_panel.setVisible(true);
            }
        });
    }

    /**
     * 找人
     */
    private void findUsers(){
        result_panel.setVisible(true);
        init_panel.setVisible(false);
        results_panel.removeAll();
        repaint();

        String searchContent = " 请输入HH号码/昵称".equals(search_field.getText()) ? "" : search_field.getText();
        List<User> searchUsers = userService.searchBy(searchContent);
        info_text.setText("搜索：" + searchContent);
        if ("".equals(searchContent) || searchUsers.size() == 0) {
            results_panel.add(noresults_text);
            repaint();
            return;
        }

        for (int i = 0; i < searchUsers.size(); i++) {
            User searchUser = searchUsers.get(i);
            AddUserPanel panel = new AddUserPanel(userId, searchUser);
            results_panel.add(panel);

            if (i == 0){
                panel.setLocation(0,0);
                continue;
            }
            panel.setLocation(i%3*panel.getWidth(),i/3*panel.getHeight());
        }
    }

    public Map<String, JFrame> getHahaFrames() {
        return hahaFrames;
    }
}
