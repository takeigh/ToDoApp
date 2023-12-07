package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarPanel extends JPanel {
    private Calendar currentMonth;

    public CalendarPanel() {
        currentMonth = Calendar.getInstance();
        setLayout(new BorderLayout());
        updateCalendar();
    }

    private void updateCalendar() {
        removeAll();

        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy");
        String monthYearString = monthYearFormat.format(currentMonth.getTime());

        JLabel monthYearLabel = new JLabel(monthYearString, SwingConstants.CENTER);
        add(monthYearLabel, BorderLayout.NORTH);

        JPanel calendarGrid = new JPanel(new GridLayout(0, 7));

        // Add day names (Sun, Mon, Tue, etc.)
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayNames) {
            calendarGrid.add(new JLabel(day, SwingConstants.CENTER));
        }

        // Set the calendar to the first day of the month
        currentMonth.set(Calendar.DAY_OF_MONTH, 1);

        // Add empty labels for days before the first day of the month
        for (int i = 1; i < currentMonth.get(Calendar.DAY_OF_WEEK); i++) {
            calendarGrid.add(new JLabel(""));
        }

        // Add labels for each day of the month
        int lastDay = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= lastDay; i++) {
            JLabel dayLabel = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            calendarGrid.add(dayLabel);
        }

        add(calendarGrid, BorderLayout.CENTER);
    }

    public void showNextMonth() {
        currentMonth.add(Calendar.MONTH, 1);
        updateCalendar();
        revalidate();
        repaint();
    }

    public void showPreviousMonth() {
        currentMonth.add(Calendar.MONTH, -1);
        updateCalendar();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calendar Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CalendarPanel calendarPanel = new CalendarPanel();
            frame.add(calendarPanel);

            JButton nextMonthButton = new JButton("Next Month");
            nextMonthButton.addActionListener(e -> calendarPanel.showNextMonth());

            JButton prevMonthButton = new JButton("Previous Month");
            prevMonthButton.addActionListener(e -> calendarPanel.showPreviousMonth());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(prevMonthButton);
            buttonPanel.add(nextMonthButton);

            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
