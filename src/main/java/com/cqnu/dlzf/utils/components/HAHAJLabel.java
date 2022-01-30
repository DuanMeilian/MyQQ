package com.cqnu.dlzf.utils.components;

import javax.swing.*;
import java.awt.*;

public class HAHAJLabel extends JLabel {

    public HAHAJLabel(String text, Color foreground, Color background, Font font, Rectangle bounds){
        super(text);
        setOpaque(true);
        setForeground(foreground);
        setBackground(background);
        setFont(font);
        setBounds(bounds);
    }
}
