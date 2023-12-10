package com.zynozin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Month;

public class CalendarMonthDisplay extends JPanel {
    static Month displayedMonth;
    static int displayedYear;
    static final String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public CalendarMonthDisplay() {
        setBackground(new Color(37, 37, 37));
        setLayout(new FlowLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(1120, 500));
        System.out.println(this.getSize());

        displayedMonth = CalendarPanel.displayedMonth;
        displayedYear = CalendarPanel.displayedYear;

        setDayHeader();
        setDayNumbers();
    }

    public void setDayHeader() {
        JPanel headers = new JPanel(new FlowLayout());

        for (int i = 0; i < 7; i++) {
            JLabel dayLabel = new JLabel(daysOfWeek[i]);
            dayLabel.setBackground(Color.LIGHT_GRAY);
            dayLabel.setForeground(Color.BLACK);
            dayLabel.setFont(Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf"));
            dayLabel.setPreferredSize(new Dimension(142,20));
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dayLabel.setOpaque(true);
            headers.add(dayLabel);
        }

        add(headers);
    }

    public void setDayNumbers() {
        JPanel dates = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        dates.setVisible(false);
        dates.removeAll();
        dates.setVisible(true);

        dates.setPreferredSize(new Dimension(1034,500));

        int d = monthStart();
        int monthDay = 1;
        int numWeeks = (int) Math.ceil((double)(displayedMonth.length(LocalDate.of(displayedYear, 1, 1).isLeapYear()) + d) / 7);

        for (int i = 1; i <= numWeeks; i++) {
            JPanel week = new JPanel(new FlowLayout());
            if (i == 1) {
                for (int j = 0; j < 7; j++) {
                    if (j < d) {
                        JLabel emptyEntry = createEmptyEntry();
                        week.add(emptyEntry);
                    } else {
                        JPanel dateEntry = createDayEntry(monthDay++);
                        week.add(dateEntry);
                    }
                }
            } else {
                for(int j = 0; j < 7; j++) {
                    if (monthDay <= displayedMonth.length(LocalDate.of(displayedYear, 1, 1).isLeapYear())) {
                        JPanel dateEntry = createDayEntry(monthDay++);
                        week.add(dateEntry);
                    } else {
                        JLabel emptyEntry = createEmptyEntry();
                        week.add(emptyEntry);
                    }
                }
            }

            dates.add(week);
        }

        add(dates);
    }

    public int monthStart() {
        String monthStartDay = LocalDate.of(displayedYear, displayedMonth, 1).getDayOfWeek().toString();
        int d = 0;
        switch (monthStartDay) {
            case "SUNDAY":
                break;
            case "MONDAY":
                d = 1;
                break;
            case "TUESDAY":
                d = 2;
                break;
            case "WEDNESDAY":
                d = 3;
                break;
            case "THURSDAY":
                d = 4;
                break;
            case "FRIDAY":
                d = 5;
                break;
            case "SATURDAY":
                d = 6;
                break;
        }

        return d;
    }

    private JLabel createEmptyEntry() {
        JLabel emptyEntry = new JLabel();
        emptyEntry.setBorder(new LineBorder(new Color(37, 37, 37)));
        emptyEntry.setPreferredSize(new Dimension(142,45));
        emptyEntry.setOpaque(true);
        return emptyEntry;
    }

    private JPanel createDayEntry(int dayNumber) {
        JPanel dayEntry = new JPanel();
        dayEntry.setVisible(false);
        dayEntry.removeAll();
        dayEntry.setVisible(true);

        dayEntry.setBorder(new LineBorder(new Color(37, 37, 37)));
        dayEntry.setPreferredSize(new Dimension(142, 45));
        dayEntry.setOpaque(true);
        dayEntry.setLayout(new FlowLayout());

        JLabel dayNumberLabel = new JLabel(Integer.toString(dayNumber));
        dayNumberLabel.setOpaque(false);
        dayNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dayNumberLabel.setVerticalAlignment(SwingConstants.TOP);
        dayNumberLabel.setForeground(Color.BLACK);
        dayNumberLabel.setPreferredSize(new Dimension(142, 15));
        dayNumberLabel.setBorder(new EmptyBorder(0,0,0,8));

        int taskNum = countTasks(dayNumber);

        JLabel taskCount = new JLabel("Tasks Due: " + taskNum);
        taskCount.setOpaque(false);
        taskCount.setHorizontalAlignment(SwingConstants.LEFT);
        taskCount.setVerticalAlignment(SwingConstants.BOTTOM);
        taskCount.setForeground(Color.BLACK);
        taskCount.setPreferredSize(new Dimension(142, 20));
        taskCount.setBorder(new EmptyBorder(0,8,0,0));

        dayEntry.add(dayNumberLabel);
        if (taskNum != 0) {
            dayEntry.add(taskCount);
        }
        return dayEntry;
    }

    public void updateDisplay() {
        displayedMonth = CalendarPanel.displayedMonth;
        displayedYear = CalendarPanel.displayedYear;

        this.setVisible(false);
        removeAll();

        setDayHeader();
        setDayNumbers();
        this.setVisible(true);
    }

    public int countTasks(int dayNumber) {
        int count = 0;
        try {
            BufferedReader next = new BufferedReader(new FileReader("files/nextUp.txt"));
            BufferedReader inProgress = new BufferedReader(new FileReader("files/inProgress.txt"));

            String nextLine = next.readLine();
            String inProgressLine = inProgress.readLine();

            int currentMonth = displayedMonth.getValue();

            while (nextLine != null) {
                nextLine = nextLine.substring(nextLine.indexOf(',') + 1);
                int year = Integer.parseInt(nextLine.substring(0, 4));
                int month = Integer.parseInt(nextLine.substring(5, 7));
                int day = Integer.parseInt(nextLine.substring(8, 10));

                if (year == displayedYear && month == currentMonth && day == dayNumber) {
                    count++;
                }

                nextLine = next.readLine();
            }

            while (inProgressLine != null) {
                inProgressLine = inProgressLine.substring(inProgressLine.indexOf(',') + 1);
                int year = Integer.parseInt(inProgressLine.substring(0, 4));
                int month = Integer.parseInt(inProgressLine.substring(5, 7));
                int day = Integer.parseInt(inProgressLine.substring(8, 10));

                if (year == displayedYear && month == currentMonth && day == dayNumber) {
                    count++;
                }

                inProgressLine = inProgress.readLine();
            }

        } catch (Exception ignored){

        }

        return count;
    }
}
