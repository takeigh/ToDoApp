package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewTask extends JLabel implements MouseListener {
    private Font newFont = Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf");
    String title;
    private String dueDate = "";

    public NewTask(String title) {
        this.title = title;
        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setText("+ New");
        setForeground(Color.LIGHT_GRAY);
        if (title.equals("taskslist"))
            this.setPreferredSize(new Dimension(320, 30));
        else if (title.equals("grocery list vegetables") || title.equals("grocery list fruits") ||
                title.equals("grocery list drinks") || title.equals("grocery list other"))
            this.setPreferredSize(new Dimension(250, 30));
        else if (title.equals("reading journal"))
            this.setPreferredSize(new Dimension(360, 30));
        else
            this.setPreferredSize(new Dimension(900, 30));
        this.addMouseListener(this);
        setFont(newFont);

        JButton dueDateButton = new JButton("Due Date");
        dueDateButton.setPreferredSize(new Dimension(100, 30));
        dueDateButton.addActionListener(e -> setDueDate());
        this.add(dueDateButton);

        this.addMouseListener(this);
        setFont(newFont);
    }

    private void setDueDate() {
        String userInput = JOptionPane.showInputDialog(null, "Enter Due Date (YYYY-MM-DD):");
        if (userInput != null && !userInput.isEmpty()) {
            dueDate = userInput;
            JOptionPane.showMessageDialog(null, "Due Date set to: " + dueDate);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle the click event as needed
        // You can access the dueDate variable here and use it as needed
        System.out.println("Due Date: " + dueDate);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (title.equals("taskslist")) {
            ContentDataLabel contentDataLabel = new ContentDataLabel();
            this.setVisible(false);
            ContentDataPanel.nextCategory.add(contentDataLabel);
            ContentDataPanel.nextCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastTasksSave.add(contentDataLabel);
            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
            if (taskDescription != null && !taskDescription.isEmpty()) {
                contentDataLabel.contentDataArea.setText(taskDescription);

                // Prompt the user for the due date
                String dueDateStr = JOptionPane.showInputDialog("Enter due date (yyyy-MM-dd):");

                try {
                    // Parse the due date and set it if provided
                    if (dueDateStr != null && !dueDateStr.isEmpty()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date dueDate = dateFormat.parse(dueDateStr);
                        contentDataLabel.setDueDate(dueDate);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Add the task to the next category
                ContentDataPanel.nextCategory.add(contentDataLabel);
                ContentDataPanel.lastTasksSave.add(contentDataLabel);
            }

        } else if (title.equals("grocery list vegetables")) {
            ListOfItems vegetablesList = new ListOfItems("false", "grocery list vegetables");
            this.setVisible(false);
            ContentDataPanel.vegetablesCategory.add(vegetablesList);
            ContentDataPanel.vegetablesCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastVegetablesListSave.add(vegetablesList);
        } else if (title.equals("grocery list fruits")) {
            ListOfItems fruitsList = new ListOfItems("false", "grocery list fruits");
            this.setVisible(false);
            ContentDataPanel.fruitsCategory.add(fruitsList);
            ContentDataPanel.fruitsCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastFruitsListSave.add(fruitsList);
        } else if (title.equals("grocery list drinks")) {
            ListOfItems drinksList = new ListOfItems("false", "grocery list drinks");
            this.setVisible(false);
            ContentDataPanel.drinksCategory.add(drinksList);
            ContentDataPanel.drinksCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastDrinksListSave.add(drinksList);
        } else if (title.equals("grocery list other")) {
            ListOfItems otherList = new ListOfItems("false", "grocery list other");
            this.setVisible(false);
            ContentDataPanel.otherCategory.add(otherList);
            ContentDataPanel.otherCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastOtherListSave.add(otherList);
        } else if (title.equals("checklist")) {
            ListOfItems checklistItems = new ListOfItems("false", "checklist");
            this.setVisible(false);
            MainContent.checklistPanel.add(checklistItems);
            MainContent.checklistPanel.add(this);
            this.setVisible(true);
            ItemsPanel.lastChecklistSave.add(checklistItems);
        } else if (title.equals("reading journal")) {
            ListOfItems bookItem = new ListOfItems(ListOfItems.bookIcon, "book", 14f, 35, 330, 360, true, true);
            ListOfItems startedItem = new ListOfItems(ListOfItems.bookIcon, "started", 14f, 35, 330, 360, false, true);
            ListOfItems finishedItem = new ListOfItems(ListOfItems.bookIcon, "finished", 14f, 35, 330, 360, false, true);
            this.setVisible(false);
            ContentDataPanel.bookNameCategory.add(bookItem);
            ContentDataPanel.startedCategory.add(startedItem);
            ContentDataPanel.finishedCategory.add(finishedItem);
            ContentDataPanel.bookNameCategory.add(this);
            this.setVisible(true);
            ContentDataPanel.lastBookListSave.add(bookItem);
            ContentDataPanel.lastStartedListSave.add(startedItem);
            ContentDataPanel.lastFinishedListSave.add(finishedItem);
        } else if (title.equals("wishlist")) {
            ListOfItems wishItems = new ListOfItems(ListOfItems.wishIcon, "wish", 18f, 80, 600, 900, true, true);
            this.setVisible(false);
            MainContent.wishlistPanel.add(wishItems);
            MainContent.wishlistPanel.add(this);
            this.setVisible(true);
            ItemsPanel.lastWishlistSave.add(wishItems);
        }
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
