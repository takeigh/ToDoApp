package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;

public class CalendarPanel extends JPanel {
    CalendarMonthDisplay calendarDisplay = new CalendarMonthDisplay();

    CalendarMonthSwapper prevMonthButton = new CalendarMonthSwapper("Previous", this);
    CalendarMonthSwapper nextMonthButton = new CalendarMonthSwapper("Next", this);

    static Month displayedMonth = LocalDate.now().getMonth();
    static int displayedYear = LocalDate.now().getYear();
    JLabel monthLabel = new JLabel(displayedMonth.name() + " " + displayedYear);

    JPanel headerPanel = new JPanel();

    public CalendarPanel() {
        panelLayout();
    }

    public void panelLayout() {
        // Set the Calendar Layout
        setBackground(new Color(37, 37, 37));
        setOpaque(true);
        setPreferredSize(new Dimension(1120, 10000));
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
}
