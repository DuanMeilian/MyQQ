package com.cqnu.dlzf.utils.factory;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileChooserFactory {

	public static JFileChooser getJFileChooser() {
		JFileChooser chooser = new JFileChooser("D:/");
		chooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
		chooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
		chooser.setFileFilter(new FileNameExtensionFilter("所有图片文件", "jpg","bmp","png","gif"));
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileHidingEnabled(false);
		return chooser;
	}
}
