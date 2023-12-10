package com.zynozin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Month;

public class CalendarMonthSwapper extends JLabel implements MouseListener {
    public final String functionality;
    private Month newDisplayedMonth;
    private int newDisplayedYear;
    private final CalendarPanel panel;

    public CalendarMonthSwapper(String functionality, CalendarPanel panel) {
        setBackground(new Color(206,206,206));
        setOpaque(true);
        setForeground(Color.BLACK);
        setFont(Main.getFontforApp(18f, "fonts/Montserrat-Regular.ttf"));
        addMouseListener(this);
        setPreferredSize(new Dimension(200, 40));
        setHorizontalAlignment(SwingConstants.CENTER);
        this.panel = panel;

        this.functionality = functionality;

        switch (functionality) {
            case "Previous":
                setText("Previous Month");
                break;
            case "Next":
                setText("Next Month");
                break;
        }
    }

    public void nextMonth() {
        newDisplayedMonth = CalendarPanel.displayedMonth.plus(1);
        newDisplayedYear = CalendarPanel.displayedYear;

        if (CalendarPanel.displayedMonth.equals(Month.DECEMBER)) {
            newDisplayedYear++;
        }

        CalendarPanel.displayedMonth = newDisplayedMonth;
        CalendarPanel.displayedYear = newDisplayedYear;

        panel.updateCalendar();
    }

    public void prevMonth() {
        newDisplayedMonth = CalendarPanel.displayedMonth.minus(1);
        newDisplayedYear = CalendarPanel.displayedYear;

        if (CalendarPanel.displayedMonth.equals(Month.JANUARY)) {
            newDisplayedYear--;
        }

        CalendarPanel.displayedMonth = newDisplayedMonth;
        CalendarPanel.displayedYear = newDisplayedYear;

        panel.updateCalendar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (functionality.equals("Previous")) {
            prevMonth();
        } else if (functionality.equals("Next")) {
            nextMonth();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(new Color(206,206,206));
    }
}
