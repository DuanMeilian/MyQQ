package com.cqnu.dlzf.utils.factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TipFactory extends JLabel{

	private static final long serialVersionUID = 1L;

	public static JLabel createTip(String tipContent, int x, int y, int width, int height) {
		JLabel tipJLabel = new JLabel(tipContent);
		tipJLabel.setForeground(new Color(192,91,106));
		tipJLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		tipJLabel.setBounds(x, y, width, height);
		tipJLabel.setVisible(false);
		return tipJLabel;
	}
}