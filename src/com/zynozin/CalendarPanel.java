package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class CalendarPanel extends JPanel {
    public CalendarMonthDisplay calendarDisplay = new CalendarMonthDisplay();

    public CalendarMonthSwapper prevMonthButton = new CalendarMonthSwapper("Previous", this);
    public CalendarMonthSwapper nextMonthButton = new CalendarMonthSwapper("Next", this);

    public static Month displayedMonth = LocalDate.now().getMonth();
    public static int displayedYear = LocalDate.now().getYear();
    JLabel monthLabel = new JLabel(displayedMonth.name() + " " + displayedYear);

    public JPanel headerPanel = new JPanel();

    public CalendarPanel() {
        panelLayout();
    }

    public void panelLayout() {
        // Set the Calendar Layout
        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setPreferredSize(new Dimension(1120, 1000));
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        setBorder(new EmptyBorder(0, 35, 0, 0));

        headerLayout();

        add(headerPanel);
        add(calendarDisplay);
    }

    public void headerLayout() {
        // Set the header layout
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(37, 37, 37));
        headerPanel.setForeground(Color.LIGHT_GRAY);
        headerPanel.setOpaque(true);
        headerPanel.setPreferredSize(new Dimension(1120, 50));
        headerPanel.setFont(Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf"));

        monthLabel.setForeground(Color.LIGHT_GRAY);
        monthLabel.setBackground(new Color(37,37,37));
        monthLabel.setPreferredSize(new Dimension(600, 30));
        monthLabel.setOpaque(true);
        monthLabel.setFont(Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf"));
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(prevMonthButton);
        headerPanel.add(monthLabel);
        headerPanel.add(nextMonthButton);
    }

    public void updateCalendar() {
        setVisible(false);
        removeAll();

        reloadFiles();

        monthLabel.setText(displayedMonth.name() + " " + displayedYear);

        headerPanel.removeAll();
        headerPanel.add(prevMonthButton);
        headerPanel.add(monthLabel);
        headerPanel.add(nextMonthButton);

        add(headerPanel);

        calendarDisplay.removeAll();
        calendarDisplay.updateDisplay();

        add(calendarDisplay);

        setVisible(true);
    }

    public void reloadFiles() {
        try {
            BufferedWriter nextWriter = new BufferedWriter(new FileWriter("files/nextUp.txt"));
            BufferedWriter inProgressWriter = new BufferedWriter(new FileWriter("files/inProgress.txt"));
            BufferedWriter completedWriter = new BufferedWriter(new FileWriter("files/completed.txt"));

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
            nextWriter.close();
            inProgressWriter.close();
            completedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
