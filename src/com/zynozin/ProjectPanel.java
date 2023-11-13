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
    public static List<ProjectEntry> lastIdeaListSave = new ArrayList<>();
    static ProjectPanel panel;

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

    public static ProjectPanel getProjectPanel() throws IOException {
        if (panel == null) {
            panel = new ProjectPanel();
        }

        return panel;
    }

    public void writeSavedElements() throws IOException {
        BufferedReader idea = new BufferedReader(new FileReader("files/ideas.txt"));
        String ideaLine = idea.readLine();
        String[] entryString;

        try {
            while (ideaLine != null) {
                if (ideaLine.contains(",")) {
                    entryString = ideaLine.split(",", 2);
                } else {
                    entryString = new String[2];
                    entryString[0] = ideaLine;
                    entryString[1] = "";
                }

                ProjectEntry entry = new ProjectEntry();
                entry.textFields[0].setText(entryString[0]);
                entry.textFields[1].setText((entryString[1]));

                entry.setPreferredSize(new Dimension(900, 100));
                entry.setBorder(new EmptyBorder(1,1,1,1));
                entry.titleField.setPreferredSize(new Dimension(900, 30));
                entry.titleField.setBorder(new EmptyBorder(5,5,5,5));
                entry.descriptionField.setPreferredSize((new Dimension(900, 500)));
                entry.descriptionField.setBorder(new EmptyBorder(5,5,10,5));

                this.add(entry);
                lastIdeaListSave.add(entry);
                ideaLine = idea.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            idea.close();
        }
    }

    public void saveProjects() throws IOException {
        BufferedWriter ideaWriter = new BufferedWriter(new FileWriter("files/ideas.txt",false));
        try {
            for (ProjectEntry entry : ProjectPanel.lastIdeaListSave) {
                String projectTitle = entry.textFields[0].getText();
                String projectDescription = entry.textFields[1].getText();
                if (!projectTitle.isEmpty()) {
                    ideaWriter.write(projectTitle + "," + projectDescription);
                }
                ideaWriter.newLine();
            }
        } catch (IOException e) {
        } finally {
            ideaWriter.close();
        }
    }
}
