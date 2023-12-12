package tests;

import com.zynozin.NewTask;
import com.zynozin.NotesPanel;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskAndNoteCreationTests {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private NewTask newTask;
    private NotesPanel notesPanel;

    @BeforeEach
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
}
