package com.zynozin;

import javax.swing.*;
import java.awt.*;

public class MenuCommands extends JPanel {
    private MenuOption calendar = new MenuOption("Calendar");
    private MenuOption tasksList = new MenuOption("Tasks List");
    private MenuOption projectIdeas = new MenuOption("Project Ideas");
    private MenuOption groceryList = new MenuOption("Grocery List");
    private MenuOption checkList = new MenuOption("Checklist");
    private MenuOption readingJournal = new MenuOption("Reading Journal");
    private MenuOption notes = new MenuOption("Notes");
    private MenuOption wishList = new MenuOption("Wishlist");

    public MenuCommands() {
        this.setPreferredSize(new Dimension(178, 500));
        this.setOpaque(false);
        this.setLayout(new FlowLayout());
        this.add(calendar);
        this.add(tasksList);
        this.add(projectIdeas);
        this.add(groceryList);
        this.add(checkList);
        this.add(readingJournal);
        this.add(notes);
        this.add(wishList);
    }
}
