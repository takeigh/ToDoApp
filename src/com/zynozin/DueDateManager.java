package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DueDateManager {
    private static final String FILE_PATH = "files/dueDates.txt";

    public static void addDueDate(ContentDataLabel dueDateLabel) {
        try {
            String input = JOptionPane.showInputDialog(null, "Enter Due Date (YYYY-MM-DD):");
            LocalDate dueDate = LocalDate.parse(input);
            dueDateLabel.setDueDate(dueDate);
            saveDueDate(dueDateLabel, dueDate);
        } catch (DateTimeParseException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Please use YYYY-MM-DD.");
            addDueDate(dueDateLabel);  // Retry if invalid input
        }
    }

    public static void saveDueDate(ContentDataLabel contentDataLabel, LocalDate dueDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Save task details along with the due date
            writer.write(contentDataLabel.dueDateLabel.getText() + "," + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


