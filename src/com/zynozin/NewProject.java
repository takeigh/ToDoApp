package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewProject extends JLabel implements MouseListener {
    private final Font newFont = Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf");
    String title;

    NewProject() {
        this.title = "Project Idea";
        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setText("+ New");
        setForeground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(900, 30));
        this.addMouseListener(this);
        setFont(newFont);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ProjectEntry entry = new ProjectEntry();
        entry.setPreferredSize(new Dimension(900, 100));
        entry.setBorder(new EmptyBorder(1, 1, 1, 1));
        entry.titleField.setPreferredSize(new Dimension(900, 30));
        entry.titleField.setBorder(new EmptyBorder(5, 5, 5, 5));
        entry.descriptionField.setPreferredSize((new Dimension(900, 500)));
        entry.descriptionField.setBorder(new EmptyBorder(5, 5, 10, 5));

        this.setVisible(false);
        MainContent.ideaPanel.add(entry);
        MainContent.ideaPanel.add(this);
        this.setVisible(true);
        ProjectPanel.lastIdeaListSave.add(entry);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(37, 37, 37));
    }
}