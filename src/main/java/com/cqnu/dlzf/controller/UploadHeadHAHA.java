package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.socket.UploadHeadClient;
import com.cqnu.dlzf.utils.factory.ImageHandlerFactory;
import com.cqnu.dlzf.utils.factory.JFileChooserFactory;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.filebean.FileBean;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

import java.awt.Color;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UploadHeadHAHA extends JFrame {

    private File imgFile;

    private UserService userService = new UserServiceImpl();

    private JLabel upload_icon;
    private JLabel upload_button;
    private JLabel sure_button;
    private JLabel cancel_button;

    private Map<String, JFrame> hahaFrames;

    public static UploadHeadHAHA init(String userId, Map<String, JFrame> hahaFrames) {
        UploadHeadHAHA frame = new UploadHeadHAHA(userId, hahaFrames);
        frame.hahaFrames = hahaFrames;
        hahaFrames.put("UploadHeadHAHA",frame);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Create the frame.
     */
    public UploadHeadHAHA(String userId, Map<String, JFrame> hahaFrames) {
        createContents(userId, hahaFrames);

        UploadHeadHAHA temp = this;
        MouseAdapter clickedOpenImageAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = JFileChooserFactory.getJFileChooser();
                chooser.setDialogTitle("上传本地照片");
                chooser.setApproveButtonText("确定");

                int click = chooser.showOpenDialog(temp);
                if (click == JFileChooser.APPROVE_OPTION) {
                    // 取消选择，没有打开
                    if (chooser.getSelectedFile() == null) return;

                    imgFile =  chooser.getSelectedFile();
                    ImageIcon resizedImageIcon = ImageHandlerFactory.getResizedImageIcon(imgFile.getAbsolutePath(), 440);
                    upload_icon.setIcon(resizedImageIcon);
                    upload_icon.setBounds(21, 70,440, 436);
                    //JOptionPane.showMessageDialog(temp, "路径"+file.getAbsolutePath(),"图片上传",JOptionPane.DEFAULT_OPTION);
                }
            }
        };
        upload_button.addMouseListener(clickedOpenImageAdapter);

        MouseAdapter clickedUploadAapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeMe();
                if (imgFile == null) return;
                try {
                    UploadHeadClient client = new UploadHeadClient("127.0.0.1",9888,FileBean.getFileBean(userId,imgFile));
                    HAHAThreadPool.execute(client);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        sure_button.addMouseListener(clickedUploadAapter);
        cancel_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeMe();
            }
        });
    }

    private void closeMe(){
        dispose();
        hahaFrames.put("UploadHeadHAHA",null);
    }

    private void createContents(String userId, Map<String, JFrame> hahaFrames) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 494, 622);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);

        JPanel contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel cotent_panel = new JPanel();
        cotent_panel.setBounds(6, 6, 482, 610);
        contentPane.add(cotent_panel);
        cotent_panel.setLayout(null);

        JPanel top_panel = new JPanel();
        top_panel.setBackground(new Color(118,127,137));
        top_panel.setBounds(0, 0, 482, 40);
        cotent_panel.add(top_panel);
        top_panel.setLayout(null);

        JLabel logo_icon = new JLabel("");
        logo_icon.setIcon(new ImageIcon(UploadHeadHAHA.class.getResource("/img/user/logo.png")));
        logo_icon.setBounds(5, 5, 30, 28);
        top_panel.add(logo_icon);

        JLabel title_text = new JLabel("更换头像");
        title_text.setForeground(Color.WHITE);
        title_text.setFont(new Font("黑体", Font.PLAIN, 16));
        title_text.setBounds(38, 10, 70, 17);
        top_panel.add(title_text);

        UsualJLabelTool tool = new UsualJLabelTool(this, top_panel.getWidth(), 40);
        JLabel close = tool.getCloseJLable(top_panel.getBackground(), new Color(254,84,57));
        top_panel.add(close);

        JLabel minimize = tool.getMinimizeJLabel(top_panel.getBackground(), new Color(136,144,153));
        top_panel.add(minimize);

        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(0, 40, 482, 570);
        cotent_panel.add(main_panel);
        main_panel.setLayout(null);

        upload_button = new JLabel("上传本地照片");
        upload_button.setIcon(new ImageIcon(UploadHeadHAHA.class.getResource("/img/upload_icon.png")));
        upload_button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        upload_button.setFont(new Font("黑体", Font.PLAIN, 14));
        upload_button.setHorizontalAlignment(SwingConstants.CENTER);
        upload_button.setBounds(161, 15, 160, 40);
        upload_button.setBackground(Color.WHITE);
        upload_button.setOpaque(true);
        UsualJLabelTool.addMouseEnteredAndExitedCB(upload_button, Color.WHITE, new Color(248,248,248));
        main_panel.add(upload_button);

        sure_button = new JLabel("确定");
        sure_button.setForeground(Color.WHITE);
        sure_button.setOpaque(true);
        sure_button.setBackground(new Color(91,119,172));
        sure_button.setFont(new Font("黑体", Font.PLAIN, 14));
        sure_button.setHorizontalAlignment(SwingConstants.CENTER);
        sure_button.setBounds(140, 520, 90, 30);
        UsualJLabelTool.addMouseEnteredAndExitedCB(sure_button, new Color(91,119,172), new Color(74,102,148));
        main_panel.add(sure_button);

        cancel_button = new JLabel("取消");
        cancel_button.setOpaque(true);
        cancel_button.setHorizontalAlignment(SwingConstants.CENTER);
        cancel_button.setForeground(Color.BLACK);
        cancel_button.setFont(new Font("黑体", Font.PLAIN, 14));
        cancel_button.setBackground(Color.WHITE);
        cancel_button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        cancel_button.setBounds(250, 520, 90, 30);
        UsualJLabelTool.addMouseEnteredAndExitedCB(cancel_button, Color.WHITE, new Color(248,248,248));
        main_panel.add(cancel_button);

        ImageIcon resizedImageIcon = ImageHandlerFactory.getResizedImageIcon(userService.getUserById(userId).getImage(), 440);
        upload_icon = new JLabel("");
        upload_icon.setIcon(resizedImageIcon);
        upload_icon.setBounds(21, 70, 440, 436);
        main_panel.add(upload_icon);
    }

    public Map<String, JFrame> getHahaFrames() {
        return hahaFrames;
    }
}
