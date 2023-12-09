package tests;

import com.zynozin.NewTask;
import org.junit.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NewTaskTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testMouseClickedWithValidDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("Test Task Description");
        JTextField dueDateField = new JTextField("2025-12-31");
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);
        newTask.dueDate = dueDateField.getText();

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }

        // Assert: Check if the due date is printed correctly
        assertEquals("Due Date: 2025-12-31" + System.lineSeparator() + "Task Description: Test Task Description" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testEmptyTaskDescription() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("");
        JTextField dueDateField = new JTextField("");
        // Empty task description
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the appropriate message is printed
        assertEquals("" + "", outputStreamCaptor.toString());
    }

    @Test
    public void testEmptyDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("Test Task Description");
        JTextField dueDateField = new JTextField("");  // Empty due date
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the due date is printed as empty
        assertEquals("Invalid date format: " + System.lineSeparator() + "", outputStreamCaptor.toString());
    }

    @Test
    public void testEmptyTaskDescriptionAndDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("");  // Empty task description
        JTextField dueDateField = new JTextField("");  // Empty due date
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the appropriate messages are printed
        assertEquals("" + "", outputStreamCaptor.toString());

    }

    @Test
    public void testInvalidTaskDescription() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField(null);  // Invalid task description
        JTextField dueDateField = new JTextField("2023-12-31");
        newTask.setDueDateField(dueDateField);
        newTask.setTaskField(taskField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the appropriate message is printed
        assertEquals("" + "", outputStreamCaptor.toString());
    }

    @Test
    public void testEmptyTaskDescriptionAndInvalidDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("");  // Empty task description
        JTextField dueDateField = new JTextField("");
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the appropriate messages are printed
        assertEquals("" + "", outputStreamCaptor.toString());
    }

    @Test
    public void testInvalidTaskDescriptionAndDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField(null);  // Invalid task description
        JTextField dueDateField = new JTextField("invalid_date");
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }
        // Assert: Check if the appropriate messages are printed
        assertEquals("" + "", outputStreamCaptor.toString());

    }

    @Test
    public void testPastDueDate() {
        // Arrange
        NewTask newTask = new NewTask("taskslist");
        JTextField taskField = new JTextField("Test Task Description");
        JTextField dueDateField = new JTextField("2022-01-01");  // Past due date
        newTask.setTaskField(taskField);
        newTask.setDueDateField(dueDateField);

        // Act
        if (newTask.isTaskDescriptionValid(taskField.getText()) && newTask.isDueDateValid(dueDateField.getText())) {
            newTask.mouseClicked(new MouseEvent(newTask, 0, 0, 0, 0, 0, 0, false));
        }

        // Assert: Check if the appropriate message is printed
        assertEquals("Due Date is in the past: 2022-01-01" + System.lineSeparator() + "", outputStreamCaptor.toString());
    }

}