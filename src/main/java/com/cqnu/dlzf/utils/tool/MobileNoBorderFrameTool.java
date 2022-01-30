package com.cqnu.dlzf.utils.tool;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.controller.UserHAHA;
import com.cqnu.dlzf.controller.UserInfoHAHA;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.components.LoginTipDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MobileNoBorderFrameTool {

    private UserInfoHAHA userInfoHAHA;

    // 用户聊天栏
    private UserHAHA userHAHA;

    // 登录用户
    private User loginUser;

    // 移动窗体时，必须关闭tipDialog
    private LoginTipDialog tipDialog;

    // 用户签名框
    private JTextField signText;

    // 签名框的默认颜色
    private Color signColor;

    // UserInfoHAHA的用户生日输入框
    private JLabel birthdayText;

    // UserInfoHAHA的用户生日选择域
    private JPanel birthdayPanel;

    private Point OriginalPoint=null,MobileDistance=null;		//鼠标移动前的位置和鼠标移动的距离
    private boolean isDraging = false;		//用与判断鼠标书否松开

    public void setTipDialog(LoginTipDialog tip) {
        tipDialog = tip;
    }

    public void setTextField(JTextField text) {
        signText = text;
    }

    public void setSignTextColor(Color signColor) {
        this.signColor = signColor;
    }

    public void setLoginUser(User loginUser){this.loginUser = loginUser;}

    public void setUserHAHA(UserHAHA userHAHA) {
        this.userHAHA = userHAHA;
    }

    public void setUserInfoHAHA(UserInfoHAHA userInfoHAHA) {
        this.userInfoHAHA = userInfoHAHA;
    }

    public void setBirthdayText(JLabel birthdayText) {
        this.birthdayText = birthdayText;
    }

    public void setBirthdayPanel(JPanel birthdayPanel) {
        this.birthdayPanel = birthdayPanel;
    }

    public MobileNoBorderFrameTool(Component frame){
        super();

        OriginalPoint=new Point();		//鼠标移动前的位置
        MobileDistance=new Point();		//鼠标移动的距离

        /*确保取消标题栏以及边框以后，窗口仍然能接受鼠标点击事件*/
        frame.addMouseListener(new MouseAdapter() {		//按下鼠标时触发的动作
            public void mousePressed(MouseEvent e) {
                if (tipDialog != null){
                    tipDialog.setVisible(false);
                }

                isDraging = true;
                OriginalPoint.x = e.getX();		//获取鼠标移动前的位置
                OriginalPoint.y = e.getY();

                if (birthdayText != null) {
                    birthdayText.setBorder(BorderFactory.createLineBorder(new Color(218, 218, 218), 2));

                    if (birthdayPanel != null) birthdayPanel.setVisible(false);
                }

                updateSignText();
                //System.out.println("悬浮窗FloatingWindow或者常规窗口ConventionalWindow上的鼠标已按下...");
            }

            public void mouseReleased(MouseEvent e) {		//松开鼠标触发的事件
                isDraging = false;
                //System.out.println("悬浮窗FloatingWindow或者常规窗口ConventionalWindow上的鼠标已松开");
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {		//鼠标移动时的动作
            public void mouseDragged(MouseEvent e) {
                if (tipDialog != null){
                    tipDialog.setVisible(false);

                    tipDialog.setLocationRelativeTo(frame);
                    int y = tipDialog.getY();
                    tipDialog.setLocation(tipDialog.getX(), y + 220);
                    setTipDialog(tipDialog);
                }

                if (isDraging) {

                    //System.out.println("鼠标移动的距离：X轴为"+MobileDistance.x +"\tY轴为"+MobileDistance.y);

                    MobileDistance= frame.getLocation();		//获取鼠标移动距离
                    /*重新绘制位置*/
                    frame.setLocation(MobileDistance.x + e.getX() - OriginalPoint.x, MobileDistance.y + e.getY() - OriginalPoint.y);
                }
            }
        });
    }

    /**
     * 更新签名
     */
    private void updateSignText(){
        if (signText != null){

            // 没有修改内容，点击外面，不要发送更新的sql语句
            if (signText.getBackground().equals(signColor)) return;

            signText.setCaretColor(signColor);
            signText.setBackground(signColor);
            signText.setForeground(Color.WHITE);

            String signContent = signText.getText();
            UserService userService = new UserServiceImpl();

            if ("编辑个性签名".equals(signContent)) return;
            if ("".equals(signContent)) {
                signText.setText("编辑个性签名");
            }
            loginUser.setSign(signContent);

            User updateUser = new User();
            updateUser.setUsername(loginUser.getUsername());
            updateUser.setSign(signContent);

            userService.updateUserSign(updateUser);

            // 注意：signContent和signText.getText()不同（取值的时间点不同）
            if (userHAHA != null) userHAHA.setSignText(signText.getText());
            if (userInfoHAHA != null) userInfoHAHA.refreshSign(signText.getText());
        }
    }
}