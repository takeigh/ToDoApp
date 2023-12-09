package tests;
import static org.junit.Assert.*;

import com.zynozin.NewTask;
import org.junit.Test;

import java.time.LocalDate;

public class BoundaryValueTestsDate {

    @Test
    public void testValidDateInput() {
        NewTask newTask = new NewTask("taskslist");
        assertTrue(newTask.isDueDateValid("2023-12-31"));
    }

    @Test
    public void testInvalidDateInput() {
        NewTask newTask = new NewTask("taskslist");
        assertFalse(newTask.isDueDateValid("31-12-2023"));
    }

    @Test
    public void testMinimumDateValue() {
        NewTask newTask = new NewTask("taskslist");
        assertTrue(newTask.isDueDateValid("2023-12-09"));
    }

    @Test
    public void testMaximumDateValue() {
        NewTask newTask = new NewTask("taskslist");
        assertTrue(newTask.isDueDateValid("9999-12-31"));
    }

    @Test
    public void testLeapYear() {
        NewTask newTask = new NewTask("taskslist");
        assertTrue(newTask.isDueDateValid("2024-02-29"));
    }

    @Test
    public void testInvalidLeapYear() {
        NewTask newTask = new NewTask("taskslist");
        assertFalse(newTask.isDueDateValid("2023-02-29"));
    }

    @Test
    public void testDateBeforeCurrentDate() {
        NewTask newTask = new NewTask("taskslist");
        LocalDate currentDate = LocalDate.now();
        LocalDate pastDate = currentDate.minusDays(1);
        String pastDateStr = pastDate.toString();
        assertFalse(newTask.isDueDateValid(pastDateStr));
    }

    @Test
    public void testDateAfterCurrentDate() {
        NewTask newTask = new NewTask("taskslist");
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(1);
        String futureDateStr = futureDate.toString();
        assertTrue(newTask.isDueDateValid(futureDateStr));
    }

    @Test
    public void testEmptyDueDateField() {
        NewTask newTask = new NewTask("taskslist");
        assertFalse(newTask.isDueDateValid(""));
    }

    @Test
    public void testInvalidDueDateFormat() {
        NewTask newTask = new NewTask("taskslist");
        assertFalse(newTask.isDueDateValid("31-12-2023"));
    }

    @Test
    public void testDueDateBeforeCurrentDate() {
        NewTask newTask = new NewTask("taskslist");
        assertFalse(newTask.isDueDateValid("2023-01-01"));
    }

    @Test
    public void testDueDateAfterCurrentDate() {
        NewTask newTask = new NewTask("taskslist");
        assertTrue(newTask.isDueDateValid("2023-12-31"));
    }

    @Test
    public void testDueDateSameAsCurrentDate() {
        NewTask newTask = new NewTask("taskslist");
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.toString();
        assertFalse(newTask.isDueDateValid(currentDateStr));
    }

}
