package com.cqnu.dlzf.upload;

import java.awt.EventQueue;
import java.io.File;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TestFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestFrame frame = new TestFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TestFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");

        URL resource = TestImg.class.getResource("/2.png");
        File file = new File(resource.getPath());
        TestImg.resize(file, new File("/D:/eclipseproject/MyQQ/target/test-classes/newFile.png"),72, "PNG");

        lblNewLabel.setBounds(197, 133, 72, 56);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/newFile.png")));
        contentPane.add(lblNewLabel);
    }
}
