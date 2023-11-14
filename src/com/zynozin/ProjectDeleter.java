package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectDeleter extends JLabel implements MouseListener {
    private ProjectEntry project;
    private final ImageIcon trashIcon = new ImageIcon("images/delete.png");


    public ProjectDeleter(ProjectEntry project) {
        this.project = project;
        setIcon(trashIcon);
        addMouseListener(this);
        setPreferredSize(new Dimension(30, 65));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void removeEntry() {
        ProjectPanel.lastIdeaListSave.remove(project);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        project.setVisible(false);
        removeEntry();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setOpaque(true);
        setBackground(new Color(121, 121, 121));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(new Color(37, 37, 37));
    }
}