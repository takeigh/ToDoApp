package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class CommandsLabel extends JLabel implements MouseListener {
    private ImageIcon maximizeIcon = new ImageIcon("images/maximize.png");
    private ImageIcon minimizeIcon = new ImageIcon("images/minimize.png");
    private String title;
    private Font menuFont = Main.getFontforApp(18f, "fonts/Montserrat-Bold.ttf");

    public CommandsLabel(String text) {
        this.setText(text);
        this.setForeground(new Color(233, 233, 233));
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(178, 46));
        this.setBackground(new Color(64, 64, 64));
        this.setFont(menuFont);
        this.setBorder(new EmptyBorder(0, 10, 0, 0));

    }

    public CommandsLabel(String title, ImageIcon icon) {
        this.title = title;
        this.setIcon(icon);
        this.addMouseListener(this);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(55, 46));

    }

    public static void saveTasksElements() throws IOException {

        // Write the content in file
        BufferedWriter nextWriter = new BufferedWriter(new FileWriter("files/nextUp.txt"));
        BufferedWriter inProgressWriter = new BufferedWriter(new FileWriter("files/inProgress.txt"));
        BufferedWriter completedWriter = new BufferedWriter(new FileWriter("files/completed.txt"));
        //BufferedWriter notesWriter = new BufferedWriter(new FileWriter("files/notes/notes1.txt"));
        BufferedWriter checkWriter = new BufferedWriter(new FileWriter("files/checklist.txt"));
        BufferedWriter vegetablesWriter = new BufferedWriter(new FileWriter("files/vegetables.txt"));
        BufferedWriter fruitsWriter = new BufferedWriter(new FileWriter("files/fruits.txt"));
        BufferedWriter drinksWriter = new BufferedWriter(new FileWriter("files/drinks.txt"));
        BufferedWriter otherWriter = new BufferedWriter(new FileWriter("files/other.txt"));
        BufferedWriter checkIconWriter = new BufferedWriter(new FileWriter("files/checklistIcon.txt"));
        BufferedWriter vegetablesIconWriter = new BufferedWriter(new FileWriter("files/vegetablesIcon.txt"));
        BufferedWriter fruitsIconWriter = new BufferedWriter(new FileWriter("files/fruitsIcon.txt"));
        BufferedWriter drinksIconWriter = new BufferedWriter(new FileWriter("files/drinksIcon.txt"));
        BufferedWriter otherIconWriter = new BufferedWriter(new FileWriter("files/otherIcon.txt"));
        BufferedWriter wishWriter = new BufferedWriter(new FileWriter("files/wishes.txt"));
        BufferedWriter bookWriter = new BufferedWriter(new FileWriter("files/books.txt"));
        BufferedWriter startedWriter = new BufferedWriter(new FileWriter("files/started.txt"));
        BufferedWriter finishedWriter = new BufferedWriter(new FileWriter("files/finished.txt"));
        try {
            for (ContentDataLabel tskLabel : ContentDataPanel.lastTasksSave) {
                String lineContent = tskLabel.contentDataArea.getText();
                if (tskLabel.currentCategory == "next category") {
                    nextWriter.write(lineContent.replace("\n", " ").replace("\r", " ") + "," + tskLabel.dueDateLabel.getText().substring(5));
                    nextWriter.newLine();
                } else if (tskLabel.currentCategory == "in progress category") {
                    inProgressWriter.write(lineContent.replace("\n", " ").replace("\r", " ") + "," + tskLabel.dueDateLabel.getText().substring(5));
                    inProgressWriter.newLine();
                } else if (tskLabel.currentCategory == "completed category") {
                    completedWriter.write(lineContent.replace("\n", " ").replace("\r", " ") + "," + tskLabel.dueDateLabel.getText().substring(5));
                    completedWriter.newLine();
                }
            }
            for (ListOfItems listOfItems : ItemsPanel.lastChecklistSave) {
                String checkContent = listOfItems.textField.getText();
                String checkIcon = listOfItems.checklistBox.isChecked.toString();
                checkWriter.write(checkContent);
                checkIconWriter.write(checkIcon);
                checkWriter.newLine();
                checkIconWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastVegetablesListSave) {
                String vegetablesContent = listOfItems.textField.getText();
                String vegetablesIcon = listOfItems.checklistBox.isChecked.toString();
                vegetablesWriter.write(vegetablesContent);
                vegetablesIconWriter.write(vegetablesIcon);
                vegetablesWriter.newLine();
                vegetablesIconWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastFruitsListSave) {
                String fruitsContent = listOfItems.textField.getText();
                String fruitsIcon = listOfItems.checklistBox.isChecked.toString();
                fruitsWriter.write(fruitsContent);
                fruitsIconWriter.write(fruitsIcon);
                fruitsWriter.newLine();
                fruitsIconWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastDrinksListSave) {
                String drinksContent = listOfItems.textField.getText();
                String drinksIcon = listOfItems.checklistBox.isChecked.toString();
                drinksWriter.write(drinksContent);
                drinksIconWriter.write(drinksIcon);
                drinksWriter.newLine();
                drinksIconWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastOtherListSave) {
                String otherContent = listOfItems.textField.getText();
                String otherIcon = listOfItems.checklistBox.isChecked.toString();
                otherWriter.write(otherContent);
                otherIconWriter.write(otherIcon);
                otherWriter.newLine();
                otherIconWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastBookListSave) {
                String bookContent = listOfItems.textField.getText();
                bookWriter.write(bookContent);
                bookWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastStartedListSave) {
                String startedContent = listOfItems.textField.getText();
                startedWriter.write(startedContent);
                startedWriter.newLine();
            }
            for (ListOfItems listOfItems : ContentDataPanel.lastFinishedListSave) {
                String finishedContent = listOfItems.textField.getText();
                finishedWriter.write(finishedContent);
                finishedWriter.newLine();
            }
            for (ListOfItems wishItems : ItemsPanel.lastWishlistSave) {
                String wishContent = wishItems.textField.getText();
                wishWriter.write(wishContent);
                wishWriter.newLine();
            }
            //String notesContent = MainContent.notesPanel.noteSheets.get("Sheet 1").getText();
            //notesWriter.write(notesContent);
            nextWriter.close();
            inProgressWriter.close();
            completedWriter.close();
            //notesWriter.close();
            checkWriter.close();
            checkIconWriter.close();
            wishWriter.close();
            vegetablesWriter.close();
            fruitsWriter.close();
            drinksWriter.close();
            otherWriter.close();
            vegetablesIconWriter.close();
            fruitsIconWriter.close();
            drinksIconWriter.close();
            otherIconWriter.close();
            bookWriter.close();
            startedWriter.close();
            finishedWriter.close();
        } catch (IOException e) {
            // Exception handling
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (title == "close") {
            try {
                saveTasksElements();
                MainContent.notesPanel.saveAllNoteSheets();
                ProjectPanel projects = ProjectPanel.getProjectPanel();
                projects.saveProjects();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ContentDataPanel.lastTasksSave.clear();
            Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.frame.dispose();
            System.exit(0);
        }
        if (title == "hide") {
            Main.frame.setExtendedState(JFrame.ICONIFIED);
        }/* else {
            if (title == "maximize") {
                Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.setIcon(minimizeIcon);
                this.title = "minimize";
            } else if (title == "minimize") {
                Main.frame.setExtendedState(JFrame.NORMAL);
                this.setIcon(maximizeIcon);
                this.title = "maximize";
            }

        }
        */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (title == "close") {
            this.setOpaque(true);
            this.setBackground(Color.RED);
        } else {
            this.setOpaque(true);
            this.setBackground(new Color(121, 121, 121));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        this.setBackground(new Color(37, 37, 37));
    }
}
