package com.cqnu.dlzf.utils.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class BorderJTextField extends JTextField{

	private static final long serialVersionUID = 1L;

	public BorderJTextField(String text, Color originalColor, Color changedColor) {
		super(text);
		setBorder(BorderFactory.createLineBorder(originalColor, 2));
		setFont(new Font("黑体", Font.PLAIN, 15));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(changedColor, 2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(originalColor, 2));
			}
		});
	}
}