package com.cqnu.dlzf.utils.components;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTipDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JLabel warn_content;

	public static void init(){
		LoginTipDialog dialog = new LoginTipDialog("该用户不存在。");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public LoginTipDialog(String content) {
		setUndecorated(true);
		setSize(526,32);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBackground(new Color(249, 243, 211));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel warn_icon = new JLabel("");
		warn_icon.setIcon(new ImageIcon(getClass().getResource("/img/warn.png")));
		warn_icon.setBounds(8, 5, 41, 25);
		contentPanel.add(warn_icon);

		warn_content = new JLabel(content);
		warn_content.setFont(new Font("宋体", Font.PLAIN, 14));
		warn_content.setBounds(36, 5, 334, 27);
		contentPanel.add(warn_content);

		JLabel tip_hide = new JLabel("");
		tip_hide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		tip_hide.setIcon(new ImageIcon(LoginTipDialog.class.getResource("/img/tip_hide.png")));
		tip_hide.setBounds(490, 10, 20, 15);
		contentPanel.add(tip_hide);
	}

	public void setContent(String content) {
		warn_content.setText(content);
	}
}