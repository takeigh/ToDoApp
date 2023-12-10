package tests;

import com.zynozin.NotesPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MultNoteSheetsUnitTest {

    private NotesPanel notesPanel;

    @BeforeEach
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            notesPanel = new NotesPanel();
            notesPanel.createNoteSheet("TestSheet1");  // Create note sheets for testing
            notesPanel.createNoteSheet("TestSheet2");
        });
    }

    @Test
    public void testCreateNoteSheet() {
        // Ensure there are 9 default note sheets initially
        assertEquals(9, notesPanel.getNoteSheetsSize());

        // Create a new note sheet
        notesPanel.createNoteSheet("TestSheet");

        // Check if the note sheet is created
        assertEquals(10, notesPanel.getNoteSheetsSize());
        assertTrue(notesPanel.doesNoteSheetExist("TestSheet"));
        assertEquals("TestSheet", notesPanel.getSelectedNoteSheet("TestSheet"));

        // Try creating another note sheet with the same name (should not be allowed)
        notesPanel.createNoteSheet("TestSheet");
        assertEquals(10, notesPanel.getNoteSheetsSize());

        // Create another note sheet with a different name
        notesPanel.createNoteSheet("AnotherSheet");
        assertEquals(11, notesPanel.getNoteSheetsSize());
        assertTrue(notesPanel.doesNoteSheetExist("AnotherSheet"));
    }

    @Test
    public void testSwitchNoteSheet() throws InterruptedException {
        // Create some note sheets
        notesPanel.createNoteSheet("Sheet 1");
        notesPanel.createNoteSheet("Sheet 2");
        notesPanel.createNoteSheet("Sheet 3");

        // Ensure the initial selected note sheet is the first one created
        assertEquals("Sheet 1", notesPanel.getSelectedNoteSheet("Sheet 1"));

        // Select another note sheet
        notesPanel.selectNoteSheet("Sheet 3");
        assertEquals("Sheet 3", notesPanel.getSelectedNoteSheet("Sheet 3"));

        // Select a non-existing note sheet (should not change the selection)
        notesPanel.updateNoteSheet();
        assertEquals("Sheet 3", notesPanel.getSelectedNoteSheet("Sheet 3"));
    }

    @Test
    public void testSaveAndLoadNoteSheets() throws IOException {
        // Create note sheets
        notesPanel.createNoteSheet("TestSheet1");
        notesPanel.createNoteSheet("TestSheet2");

        // Modify the content of note sheets
        notesPanel.noteSheets.get("TestSheet1").setText("Content of TestSheet1");
        notesPanel.noteSheets.get("TestSheet2").setText("Content of TestSheet2");

        // Save all note sheets
        notesPanel.saveAllNoteSheets();

        // Clear existing note sheets
        notesPanel.noteSheets.clear();
        notesPanel.noteSheetSelector.removeAllItems();

        // Load all note sheets
        notesPanel.loadAllNoteSheets();

        // Check if the content is loaded correctly from the file
        assertEquals("Content of TestSheet1", readNoteSheetFromFile("TestSheet1"));
        assertEquals("Content of TestSheet2", readNoteSheetFromFile("TestSheet2"));
    }

    private String readNoteSheetFromFile(String sheetName) throws IOException {
        BufferedReader notesReader = new BufferedReader(new FileReader("files/notes/" + sheetName + ".txt"));
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = notesReader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } finally {
            notesReader.close();
        }
        return sb.toString().trim(); // Trim to remove leading/trailing whitespaces
    }
}
