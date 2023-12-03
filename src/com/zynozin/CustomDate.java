package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDate extends JLabel {
    private final int WIDTH = 352;
    private Font dateFont = Main.getFontforApp(18f, "fonts/Montserrat-Medium.ttf");
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
    String time = now.format(formatter);

    public CustomDate() {
        this.setHorizontalAlignment(SwingConstants.RIGHT);
        this.setPreferredSize(new Dimension(WIDTH, ContentFooter.HEIGHT));
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.setForeground(new Color(255, 255, 255, 200));
        this.setBackground(new Color(37, 37, 37));
        this.setText(time + ".");
        this.setFont(dateFont);
    }
}
