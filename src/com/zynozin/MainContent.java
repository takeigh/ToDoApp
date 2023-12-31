package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainContent extends JPanel {
    private final int WIDTH = 1122;
    private final int HEIGHT = 654;

    public static ContentHeader[] contentHeaders = {
            new ContentHeader("images/tasks.png", "A simple Tasks list template that can help you organize your daily tasks smoothly.", "Tasks List."),
            new ContentHeader("images/project.png", "Have a project idea in mind? Just write it down here and come back to it later.", "Project Ideas."),
            new ContentHeader("images/groceries.png", "Organize your daily life by listing all the groceries you need to get.", "Grocery List."),
            new ContentHeader("images/checklist.png", "Write some actions that won't take much of your time.", "Checklist."),
            new ContentHeader("images/journal.png", "Started or finished from reading a book? State it here so you can keep track of it.", "Reading Journal."),
            new ContentHeader("images/notes.png", "If you have anything you would like to write and save, just write it here!", "Notes."),
            new ContentHeader("images/wishlist.png", "You want to get something but you can't at the moment? Write it here so you remember it later.", "Wishlist."),
            new ContentHeader("images/calendar.png", "A calendar to view how many tasks are due each day", "Calendar")

    };
    public ContentHeader contentHeader = contentHeaders[0];
    public ContentFooter contentFooter;
    public ContentDataPanel tasksData;
    public ContentDataPanel groceryData;
    public ContentDataPanel bookData;
    public static NotesPanel notesPanel;
    public static ItemsPanel checklistPanel;
    public static ProjectPanel ideaPanel;
    public static ItemsPanel wishlistPanel;
    public static ModernScrollPane tasksContentScrollPane;
    public static ModernScrollPane checklistScrollPane;
    public static ModernScrollPane ideaScrollPane;
    public static ModernScrollPane wishlistScrollPane;
    public static ModernScrollPane groceryScrollPane;
    public static ModernScrollPane bookScrollPane;
    public static MainContent mainContent;

    public static CalendarPanel calendarPanel;

    public MainContent() throws IOException {
        notesPanel = new NotesPanel();
        notesPanel.loadAllNoteSheets();

        calendarPanel = new CalendarPanel();

        mainContent = this;
        contentFooter = new ContentFooter();
        tasksData = new ContentDataPanel("taskslist");
        groceryData = new ContentDataPanel("grocery list");
        bookData = new ContentDataPanel("reading journal");
        notesPanel = new NotesPanel();
        checklistPanel = new ItemsPanel("checklist");
        ideaPanel = ProjectPanel.getProjectPanel();
        wishlistPanel = new ItemsPanel("wishlist");
        tasksContentScrollPane = new ModernScrollPane(tasksData);
        checklistScrollPane = new ModernScrollPane(checklistPanel);
        ideaScrollPane = new ModernScrollPane(ideaPanel);
        wishlistScrollPane = new ModernScrollPane(wishlistPanel);
        groceryScrollPane = new ModernScrollPane(groceryData);
        bookScrollPane = new ModernScrollPane(bookData);
        this.setLayout(new BorderLayout());
        this.add(contentHeader, BorderLayout.NORTH);
        this.add(tasksContentScrollPane, BorderLayout.CENTER);
        notesPanel.writeSavedElements();
        this.add(contentFooter, BorderLayout.SOUTH);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(37, 37, 37));
    }

    public void setContentHeader(ContentHeader contentHeader) {
        this.contentHeader.setVisible(false);
        this.contentHeader = contentHeader;
        this.add(this.contentHeader, BorderLayout.NORTH);
        this.contentHeader.setVisible(true);
    }

    public ContentHeader getContentHeader() {
        return contentHeader;
    }

}
