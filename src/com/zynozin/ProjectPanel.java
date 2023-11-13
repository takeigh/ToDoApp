package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectPanel extends JPanel {
    private final int WIDTH = 1120;
    private final int HEIGHT = 10000;
    private NewProject newProject;
    public static List<ListOfItems> lastIdeaListSave = new ArrayList<ListOfItems>();
    private ImageIcon checkedIcon = new ImageIcon("images/checked.png");

    public ProjectPanel() throws IOException {
        newProject = new NewProject();
        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        writeSavedElements();
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        add(newProject);
        setBorder(new EmptyBorder(50, 35, 0, 0));

    }

    public void writeSavedElements() throws IOException {
        BufferedReader idea = new BufferedReader(new FileReader("files/ideas.txt"));
        String ideaLine = idea.readLine();
        try {
            while (ideaLine != null) {
                ListOfItems listOfItems = new ListOfItems(ListOfItems.ideaIcon, "idea", 18f, 80, 600, 900, true, true);
                listOfItems.textField.setText(ideaLine);
                this.add(listOfItems);
                lastIdeaListSave.add(listOfItems);
                ideaLine = idea.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            idea.close();
        }
    }
}
