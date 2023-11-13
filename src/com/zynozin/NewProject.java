package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewProject extends JLabel implements MouseListener {
    private Font newFont = Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf");
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
        ListOfItems ideaItems = new ListOfItems(ListOfItems.ideaIcon, "idea", 18f, 80, 600, 900, true, true);
        this.setVisible(false);
        MainContent.ideaPanel.add(ideaItems);
        MainContent.ideaPanel.add(this);
        this.setVisible(true);
        ProjectPanel.lastIdeaListSave.add(ideaItems);
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
