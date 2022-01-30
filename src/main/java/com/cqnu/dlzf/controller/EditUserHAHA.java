package com.cqnu.dlzf.controller;

import com.cqnu.dlzf.bean.User;
import com.cqnu.dlzf.service.UserService;
import com.cqnu.dlzf.service.impl.UserServiceImpl;
import com.cqnu.dlzf.utils.*;
import com.cqnu.dlzf.utils.components.BorderJTextField;
import com.cqnu.dlzf.utils.components.DropShadowPanel;
import com.cqnu.dlzf.utils.pool.HAHAThreadPool;
import com.cqnu.dlzf.utils.tool.MobileNoBorderFrameTool;
import com.cqnu.dlzf.utils.tool.UsualJLabelTool;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Map;
import javax.swing.JButton;

public class EditUserHAHA extends JFrame {

    private Map<String, JFrame> hahaFrames;

    private DropShadowPanel contentPane;

    private JLabel birthday_text;
    private JPanel birthday_panel;

    private JTextArea sign_textArea;
    private JComboBox<String> blood_comboBox;
    private JComboBox<String> year_comboBox;
    private JComboBox<String> month_comboBox;
    private JComboBox<String> day_comboBox;
    private JComboBox<String> gender_comboBox;
    private BorderJTextField nickname_text;
    private BorderJTextField company_text;
    private BorderJTextField job_text;
    private BorderJTextField location_text;
    private BorderJTextField hometown_text;
    private BorderJTextField email_text;
    private BorderJTextField tel_text;

    public static EditUserHAHA init(User loginUser, Map<String, JFrame> hahaFrames) {
        EditUserHAHA frame = new EditUserHAHA(loginUser);
        frame.hahaFrames = hahaFrames;
        hahaFrames.put("EditUserHAHA",frame);

        MobileNoBorderFrameTool tool = new MobileNoBorderFrameTool(frame);
        tool.setBirthdayText(frame.birthday_text);
        tool.setBirthdayPanel(frame.birthday_panel);
        frame.setVisible(true);
        return frame;
    }

    public Map<String, JFrame> getHahaFrames() {
        return hahaFrames;
    }

    public JPanel getBirthdayPanel() {
        return birthday_panel;
    }

    public JLabel getBirthdayText() {
        return birthday_text;
    }

    /**
     * Create the frame.
     */
    public EditUserHAHA(User loginUser) {
        Color originalColor = new Color(218, 218, 218);
        Color changedColor = new Color(21, 131, 221);
        HAHAFormat hahaFormat = new HAHAFormat();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 484, 654);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0,0));

        contentPane = new DropShadowPanel(4);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel top_panel = new JPanel();
        top_panel.setBackground(new Color(91,119,172));
        top_panel.setBounds(6, 6, 472, 40);
        contentPane.add(top_panel);
        top_panel.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(EditUserHAHA.class.getResource("/img/user/logo.png")));
        logo.setBounds(7, 7, 30, 28);
        top_panel.add(logo);

        JLabel title = new JLabel("编辑资料");
        title.setFont(new Font("黑体", Font.PLAIN, 16));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(37, 7, 74, 28);
        top_panel.add(title);

        UsualJLabelTool tool = new UsualJLabelTool(this, top_panel.getWidth(), 40);

        JLabel minimize = tool.getMinimizeJLabel(top_panel.getBackground(), new Color(105,131,180));
        top_panel.add(minimize);

        JLabel close = tool.getCloseJLable(top_panel.getBackground(), new Color(211,63,39));
        top_panel.add(close);

        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.WHITE);
        main_panel.setBounds(6, 46, 472, 602);
        contentPane.add(main_panel);
        main_panel.setLayout(null);

        JPanel base_info_panel = new JPanel();
        base_info_panel.setBackground(Color.WHITE);
        base_info_panel.setBounds(0, 0, 472, 218);
        main_panel.add(base_info_panel);
        base_info_panel.setLayout(null);

        JLabel base_info_title = new JLabel("基础资料");
        base_info_title.setFont(new Font("宋体", Font.BOLD, 15));
        base_info_title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190,190,190)));
        base_info_title.setBounds(22, 5, 428, 35);
        base_info_panel.add(base_info_title);

        birthday_panel = new JPanel();
        birthday_panel.setBackground(Color.WHITE);
        birthday_panel.setBounds(98, 127, 290, 42);
        birthday_panel.setBorder(BorderFactory.createLineBorder(new Color(21, 131, 221), 2));
        birthday_panel.setVisible(false);
        birthday_panel.setLayout(null);
        base_info_panel.add(birthday_panel);

        JLabel sign_title = UsualJLabelTool.getJLabelTitle("签    名", new Rectangle(21, 150, 70, 27));
        base_info_panel.add(sign_title);

        sign_textArea = new JTextArea(loginUser.getSign());
        MouseAdapter signAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sign_textArea.setBorder(BorderFactory.createLineBorder(changedColor, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sign_textArea.setBorder(BorderFactory.createLineBorder(originalColor, 2));
            }
        };

        sign_textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sign_textArea.setFocusable(true);
                birthday_panel.setVisible(false);
            }
        });
        sign_textArea.addMouseListener(signAdapter);
        sign_textArea.setBorder(BorderFactory.createLineBorder(originalColor,2));
        sign_textArea.setBackground(Color.WHITE);
        sign_textArea.setBounds(98, 147, 350, 55);
        base_info_panel.add(sign_textArea);

        int year=2000,month=1,day=1;
        String birthDayString = "";
        Date birthday = loginUser.getBirthday();
        if (birthday != null){
            Calendar birthDayCalendar = Calendar.getInstance();
            birthDayCalendar.setTime(birthday);

            year = birthDayCalendar.get(Calendar.YEAR);

            // 在格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0；最后一个月取决于一年中的月份数。
            // 所以这个值的初始值为0，所以我们用它来表示日历月份时需要加1
            month = birthDayCalendar.get(Calendar.MONTH) + 1;
            day = birthDayCalendar.get(Calendar.DATE);

            birthDayString = year + "年" + month + "月" + day + "日";
        }
        birthday_text = new JLabel(birthDayString);
        birthday_text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (birthday_panel.isVisible()) {
                    birthday_text.setBorder(BorderFactory.createLineBorder(originalColor, 2));
                    sign_textArea.addMouseListener(signAdapter);
                }else {
                    birthday_text.setBorder(BorderFactory.createLineBorder(changedColor, 2));
                    sign_textArea.removeMouseListener(signAdapter);
                }
                birthday_panel.setVisible(!birthday_panel.isVisible());
                sign_textArea.setFocusable(!sign_textArea.isFocusable());
            }
        });
        birthday_text.setBorder(BorderFactory.createLineBorder(originalColor, 2));
        birthday_text.setFont(new Font("黑体", Font.PLAIN, 15));
        birthday_text.setHorizontalAlignment(SwingConstants.LEFT);
        birthday_text.setBounds(98, 99, 139, 27);
        base_info_panel.add(birthday_text);

        List<String> years = hahaFormat.getYears(1902);
        year_comboBox = new JComboBox<String>();
        for (int i = 0; i < years.size(); i++) {
            year_comboBox.addItem(years.get(i));
        }
        year_comboBox.setBackground(Color.WHITE);
        year_comboBox.setBounds(15, 10, 67, 23);
        year_comboBox.setSelectedItem(birthday == null ? "" : year + "年");
        birthday_panel.add(year_comboBox);

        List<String> months = hahaFormat.getMonths();
        month_comboBox = new JComboBox<String>();
        for (int i = 0; i < months.size(); i++) {
            month_comboBox.addItem(months.get(i));
        }
        month_comboBox.setSelectedItem(birthday == null ? "" : month + "月");
        month_comboBox.setBackground(Color.WHITE);
        month_comboBox.setBounds(97, 10, 67, 23);
        birthday_panel.add(month_comboBox);

        List<String> days = hahaFormat.getDays();
        day_comboBox = new JComboBox<String>();
        for (int i = 0; i < days.size()-3; i++) {
            day_comboBox.addItem(days.get(i));
        }
        day_comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String birthday = "" + (String) year_comboBox.getSelectedItem() +
                        (String) month_comboBox.getSelectedItem() +
                        (String) day_comboBox.getSelectedItem();
                birthday_text.setText(birthday);
            }
        });
        day_comboBox.setSelectedItem(birthday == null ? "" : day + "日");
        day_comboBox.setBackground(Color.WHITE);
        day_comboBox.setBounds(179, 10, 67, 23);
        birthday_panel.add(day_comboBox);

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String sb = "" + (String) year_comboBox.getSelectedItem() +
                        (String) month_comboBox.getSelectedItem() +
                        (String) day_comboBox.getSelectedItem();
                birthday_text.setText(sb);

                String month = (String)month_comboBox.getSelectedItem();
                String year = (String)year_comboBox.getSelectedItem();
                int length = day_comboBox.getItemCount();

                switch (month) {

                    // 31天
                    case "1月":case "3月":case "5月":case "7月":case "8月":case "10月":case "12月":
                        if (length == 30) {
                            day_comboBox.addItem("31日");
                        }
                        else if(length == 29) {
                            day_comboBox.addItem("30日");
                            day_comboBox.addItem("31日");
                        }
                        else if(length == 28) {
                            day_comboBox.addItem("29日");
                            day_comboBox.addItem("30日");
                            day_comboBox.addItem("31日");
                        }
                        break;

                    // 这几个月只有30天
                    case "4月":case "6月":case "9月":case "11月":
                        if (length == 31) {
                            day_comboBox.removeItem("31日");
                        }
                        else if(length == 29) {
                            day_comboBox.addItem("30日");
                        }
                        else if(length == 28) {
                            day_comboBox.addItem("29日");
                            day_comboBox.addItem("30日");
                        }
                        break;

                    case "2月":
                        // 不是闰年，二月没有29日
                        if (!hahaFormat.isLeap(Integer.parseInt(year.substring(0,4)))) {
                            day_comboBox.removeItem("29日");
                            day_comboBox.removeItem("30日");
                            day_comboBox.removeItem("31日");
                        }

                        // 闰年，二月29天
                        else {
                            if (length == 28) {
                                day_comboBox.addItem("29日");
                            }
                            day_comboBox.removeItem("30日");
                            day_comboBox.removeItem("31日");
                        }
                        break;
                }
            }
        };
        year_comboBox.addItemListener(itemListener);
        month_comboBox.addItemListener(itemListener);

        JLabel nickname_title = UsualJLabelTool.getJLabelTitle("昵    称", new Rectangle(21, 55, 70, 27));
        base_info_panel.add(nickname_title);

        nickname_text = new BorderJTextField(loginUser.getNickname(),originalColor,changedColor);
        nickname_text.setHorizontalAlignment(SwingConstants.LEFT);
        nickname_text.setBounds(98, 55, 139, 27);
        base_info_panel.add(nickname_text);

        JLabel gender_title = UsualJLabelTool.getJLabelTitle("性    别", new Rectangle(258, 55, 70, 27));
        base_info_panel.add(gender_title);

        gender_comboBox = new JComboBox<String>();
        gender_comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
        gender_comboBox.addItem("");
        gender_comboBox.addItem(" 男");
        gender_comboBox.addItem(" 女");
        gender_comboBox.setBackground(Color.WHITE);
        gender_comboBox.setBounds(338, 55, 112, 27);
        gender_comboBox.setSelectedItem(" " + loginUser.getGender());
        base_info_panel.add(gender_comboBox);

        JLabel birthday_title = UsualJLabelTool.getJLabelTitle("生    日", new Rectangle(21, 100, 70, 27));
        base_info_panel.add(birthday_title);

        JLabel blood_title = UsualJLabelTool.getJLabelTitle("血    型", new Rectangle(258, 100, 70, 27));
        base_info_panel.add(blood_title);

        blood_comboBox = new JComboBox<String>();
        blood_comboBox.addItem("");
        blood_comboBox.addItem(" A型");
        blood_comboBox.addItem(" B型");
        blood_comboBox.addItem(" O型");
        blood_comboBox.addItem(" AB型");
        blood_comboBox.addItem(" 其它血型");
        blood_comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
        blood_comboBox.setBackground(Color.WHITE);
        blood_comboBox.setBounds(338, 99, 112, 27);
        blood_comboBox.setSelectedItem(" " + loginUser.getBloodType());
        base_info_panel.add(blood_comboBox);

        JPanel more_info_panel = new JPanel();
        more_info_panel.setBackground(Color.WHITE);
        more_info_panel.setBounds(0, 216, 472, 319);
        main_panel.add(more_info_panel);
        more_info_panel.setLayout(null);

        JLabel more_info_title = new JLabel("更多资料");
        more_info_title.setFont(new Font("宋体", Font.BOLD, 15));
        more_info_title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190,190,190)));
        more_info_title.setBounds(22, 10, 428, 35);
        more_info_panel.add(more_info_title);

        JPanel bottom_panel = new JPanel();
        bottom_panel.setBackground(new Color(222,228,238));
        bottom_panel.setBounds(0, 535, 472, 67);
        main_panel.add(bottom_panel);
        bottom_panel.setLayout(null);

        JLabel close_button = new JLabel("关闭");
        close_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hahaFrames.put("EditUserHAHA",null);
                dispose();
            }
        });
        UsualJLabelTool.addMouseEnteredAndExitedCB(close_button,Color.WHITE,new Color(244,244,244));
        close_button.setBackground(Color.WHITE);
        close_button.setOpaque(true);
        close_button.setHorizontalAlignment(SwingConstants.CENTER);
        close_button.setBounds(382, 21, 67, 28);
        bottom_panel.add(close_button);

        JLabel save_button = new JLabel("保存");
        save_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveUserInfo(loginUser);

                hahaFrames.put("EditUserHAHA",null);
                dispose();
            }
        });
        UsualJLabelTool.addMouseEnteredAndExitedCB(save_button,Color.WHITE,new Color(244,244,244));
        save_button.setOpaque(true);
        save_button.setHorizontalAlignment(SwingConstants.CENTER);
        save_button.setBackground(Color.WHITE);
        save_button.setBounds(300, 21, 67, 28);
        bottom_panel.add(save_button);

        JLabel job_title = UsualJLabelTool.getJLabelTitle("职    业", new Rectangle(21, 65, 70, 27));
        more_info_panel.add(job_title);

        job_text = new BorderJTextField(loginUser.getJob(), originalColor, changedColor);
        job_text.setBounds(98, 65, 352, 27);
        more_info_panel.add(job_text);

        JLabel company_title = UsualJLabelTool.getJLabelTitle("公    司", new Rectangle(21, 107, 70, 27));
        more_info_panel.add(company_title);

        company_text = new BorderJTextField(loginUser.getCompany(), new Color(218, 218, 218), new Color(21, 131, 221));
        company_text.setBounds(98, 105, 352, 27);
        more_info_panel.add(company_text);

        JLabel location_title = UsualJLabelTool.getJLabelTitle("所在地区", new Rectangle(21, 149, 70, 27));
        more_info_panel.add(location_title);

        location_text = new BorderJTextField(loginUser.getLocation(), new Color(218, 218, 218), new Color(21, 131, 221));
        location_text.setBounds(98, 149, 352, 27);
        more_info_panel.add(location_text);

        JLabel hometown_title = UsualJLabelTool.getJLabelTitle("故    乡", new Rectangle(21, 191, 70, 27));
        more_info_panel.add(hometown_title);

        hometown_text = new BorderJTextField(loginUser.getHometown(), new Color(218, 218, 218), new Color(21, 131, 221));
        hometown_text.setBounds(98, 191, 352, 27);
        more_info_panel.add(hometown_text);

        JLabel tel_title = UsualJLabelTool.getJLabelTitle("手    机", new Rectangle(21, 233, 70, 27));
        more_info_panel.add(tel_title);

        tel_text = new BorderJTextField(loginUser.getTelephone(), new Color(218, 218, 218), new Color(21, 131, 221));
        tel_text.setBounds(98, 233, 352, 27);
        more_info_panel.add(tel_text);

        JLabel email_title = UsualJLabelTool.getJLabelTitle("邮    箱", new Rectangle(21, 275, 70, 27));
        more_info_panel.add(email_title);

        email_text = new BorderJTextField(loginUser.getEmail(), new Color(218, 218, 218), new Color(21, 131, 221));
        email_text.setBounds(98, 275, 352, 27);
        more_info_panel.add(email_text);

        // 使点击窗体隐藏生日域
        addMouseListener(new MouseAdapter() {        //按下鼠标时触发的动作
            public void mousePressed(MouseEvent e) {
                if (birthday_text != null) {
                    birthday_text.setBorder(BorderFactory.createLineBorder(new Color(218, 218, 218), 2));
                    if (birthday_panel != null) {
                        birthday_panel.setVisible(false);
                        sign_textArea.setFocusable(true);
                    }
                }
            }
        });
    }

    /**
     * 保存用户信息
     * @param loginUser
     */
    private void saveUserInfo(User loginUser){
        HAHAThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                UserService userService = new UserServiceImpl();

                User user = userService.getUserById(loginUser.getUserId());
                loginUser.setImage(user.getImage());
                loginUser.setNickname(nickname_text.getText());
                loginUser.setSign(sign_textArea.getText());
                loginUser.setCompany(company_text.getText());
                loginUser.setEmail(email_text.getText());
                loginUser.setHometown(hometown_text.getText());
                loginUser.setJob(job_text.getText());
                loginUser.setLocation(location_text.getText());
                loginUser.setTelephone(tel_text.getText());

                String blood = (String)blood_comboBox.getSelectedItem();
                loginUser.setBloodType(blood.trim());

                String gender = (String)gender_comboBox.getSelectedItem();
                loginUser.setGender(gender.trim());

                String year = (String)year_comboBox.getSelectedItem();
                String month = (String)month_comboBox.getSelectedItem();
                String day = (String)day_comboBox.getSelectedItem();
                if (!"".equals(year) && !"".equals(month) && !"".equals(day)){
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                        loginUser.setBirthday(sdf.parse(year + month + day));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    loginUser.setBirthday(null);
                }
                userService.updateUser(loginUser);

                UserInfoHAHA userInfoHAHA = (UserInfoHAHA)hahaFrames.get("UserInfoHAHA");
                UserHAHA userHAHA = (UserHAHA) hahaFrames.get("UserHAHA");
                if (userInfoHAHA != null) userInfoHAHA.refreshFrame(loginUser);
                if (userHAHA != null) userHAHA.refreshUserInfo(loginUser);

            }
        });
    }
}
