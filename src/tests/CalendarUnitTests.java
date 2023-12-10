package tests;

import com.zynozin.*;
import org.junit.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;

import static org.junit.Assert.*;

public class CalendarUnitTests {

    @Before
    public void setUp() {
        CalendarPanel.displayedMonth = LocalDate.now().getMonth();
        CalendarPanel.displayedYear = LocalDate.now().getYear();
    }

    @Test
    public void CheckInitialHeaderTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        Component[] components = calendarPanel.headerPanel.getComponents();
        for (Component component : components) {
            if (component instanceof CalendarMonthSwapper) {
                CalendarMonthSwapper button = (CalendarMonthSwapper) component;
                if (button.getText().equals("Next Month") || button.getText().equals("Previous Month")) {
                    assertTrue(true);
                } else {
                    fail();
                }
            } else if (component instanceof JLabel) {
                JLabel jLabel = (JLabel) component;
                assertEquals(jLabel.getText(), LocalDate.now().getMonth() + " " + LocalDate.now().getYear());
            }
        }
    }

    @Test
    public void CheckNextHeaderTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        calendarPanel.nextMonthButton.nextMonth();

        Component[] components = calendarPanel.headerPanel.getComponents();
        for (Component component : components) {
            if (component instanceof CalendarMonthSwapper) {
                CalendarMonthSwapper button = (CalendarMonthSwapper) component;
                if (button.getText().equals("Next Month") || button.getText().equals("Previous Month")) {
                    assertTrue(true);
                } else {
                    fail();
                }
            } else if (component instanceof JLabel) {
                JLabel jLabel = (JLabel) component;
                assertEquals(jLabel.getText(), LocalDate.now().getMonth().plus(1) + " " + LocalDate.now().plusMonths(1).getYear());
            }
        }
    }

    @Test
    public void CheckPrevHeaderTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        calendarPanel.prevMonthButton.prevMonth();

        Component[] components = calendarPanel.headerPanel.getComponents();
        for (Component component : components) {
            if (component instanceof CalendarMonthSwapper) {
                CalendarMonthSwapper button = (CalendarMonthSwapper) component;
                if (button.getText().equals("Next Month") || button.getText().equals("Previous Month")) {
                    assertTrue(true);
                } else {
                    fail();
                }
            } else if (component instanceof JLabel) {
                JLabel jLabel = (JLabel) component;
                assertEquals(jLabel.getText(), LocalDate.now().getMonth().minus(1) + " " + LocalDate.now().minusMonths(1).getYear());
            }
        }
    }

    @Test
    public void CheckWeekDaysTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        final String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        JPanel days = (JPanel)calendarPanel.calendarDisplay.getComponent(0);
        Component[] components = days.getComponents();

        for (int i = 0; i < 7; i++) {
            JLabel label = (JLabel)components[i];

            assertEquals(label.getText(), daysOfWeek[i]);
        }
    }

    @Test
    public void CheckCorrectStartDayTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        JPanel dayEntries = (JPanel)calendarPanel.calendarDisplay.getComponent(1);
        Component[] weeks = dayEntries.getComponents();

        int emptyEntries = 0;

        JPanel week1 = (JPanel)weeks[0];
        Component[] week1Days = week1.getComponents();

        for (Component c : week1Days) {
            if (c instanceof JLabel) {
                emptyEntries++;
            }
        }

        int d = calendarPanel.calendarDisplay.monthStart();

        assertEquals(d, emptyEntries);
    }

    @Test
    public void CheckCorrectNumberDaysTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        JPanel dayEntries = (JPanel)calendarPanel.calendarDisplay.getComponent(1);
        Component[] weeks = dayEntries.getComponents();

        int actualNumDays = LocalDate.now().getMonth().length(LocalDate.now().isLeapYear());
        int numDays = 0;

        for (Component co : weeks) {
            JPanel week = (JPanel)co;
            Component[] days = week.getComponents();

            for (Component c : days) {
                if (c instanceof JPanel) {
                    numDays++;
                }
            }
        }

        assertEquals(actualNumDays, numDays);
    }

    @Test
    public void CheckCorrectTaskCountTest() {
        CalendarPanel calendarPanel = new CalendarPanel();

        JPanel dayEntries = (JPanel)calendarPanel.calendarDisplay.getComponent(1);
        Component[] weeks = dayEntries.getComponents();

        int taskPerDay = 0;
        int actualTaskPerDay = 0;

        for (Component co : weeks) {
            JPanel week = (JPanel)co;
            Component[] days = week.getComponents();

            for (Component c : days) {
                if (c instanceof JPanel) {
                    JPanel dayEntry = (JPanel)c;

                    Component[] dayParts = dayEntry.getComponents();

                    if (dayParts.length == 2) {
                        JLabel dayNum = (JLabel)dayParts[0];
                        JLabel taskCount = (JLabel)dayParts[1];

                        taskPerDay = Integer.parseInt(taskCount.getText());
                        actualTaskPerDay = calendarPanel.calendarDisplay.countTasks(Integer.parseInt(dayNum.getText()));
                    }
                }

                assertEquals(actualTaskPerDay, taskPerDay);
            }
        }

        assertEquals(actualTaskPerDay, taskPerDay);
    }
}
