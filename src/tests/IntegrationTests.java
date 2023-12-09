package tests;

import com.zynozin.NewTask;
import com.zynozin.NotesPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class IntegrationTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private NewTask newTask;
    private NotesPanel notesPanel;

    @Before
    public void setUp() {
        newTask = new NewTask("taskslist");
        notesPanel = new NotesPanel();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testTaskCreationAndNoteSheetUpdate() throws NoSuchFieldException, IllegalAccessException {
        // Verify that a new task has been added to the notes panel
        assertEquals(7, notesPanel.getNoteSheetsSize());

        notesPanel.createNoteSheet("taskslist");

        // Verify that the note sheet associated with the new task is selected
        assertEquals("taskslist", notesPanel.getSelectedNoteSheet("taskslist"));
        //Verify add number of NoteSheets
        assertEquals(8, notesPanel.getNoteSheetsSize());

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
    public void testInvalidDueDate() {
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
    public void testSaveAndLoadNoteSheets() throws IOException {
        // Create a new note sheet
        notesPanel.createNoteSheet("Sheet1");

        // Simulate user input for saving and loading note sheets
        notesPanel.writeSavedElements();

        // Verify that the note sheet has been saved and loaded successfully
        assertTrue(notesPanel.doesNoteSheetExist("Sheet1"));
        assertEquals(8, notesPanel.getNoteSheetsSize());
    }
}
