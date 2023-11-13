package tests;

import com.zynozin.NotesPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class MultNoteSheetsUnitTest {

    private NotesPanel notesPanel;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            notesPanel = new NotesPanel();
        });
    }

    @Test
    public void testCreateNoteSheet() {
        // Ensure there are 3 default note sheets initially
        assertEquals(2, notesPanel.getNoteSheetsSize());

        // Create a new note sheet
        notesPanel.createNoteSheet("TestSheet");

        // Check if the note sheet is created
        assertEquals(3, notesPanel.getNoteSheetsSize());
        assertTrue(notesPanel.doesNoteSheetExist("TestSheet"));
        //assertEquals("TestSheet", notesPanel.getSelectedNoteSheet());

        // Try creating another note sheet with the same name (should not be allowed)
        notesPanel.createNoteSheet("TestSheet");
        assertEquals(3, notesPanel.getNoteSheetsSize());

        // Create another note sheet with a different name
        notesPanel.createNoteSheet("AnotherSheet");
        assertEquals(4, notesPanel.getNoteSheetsSize());
        assertTrue(notesPanel.doesNoteSheetExist("AnotherSheet"));
    }

    @Test
    public void testSwitchNoteSheet() throws InterruptedException {
        // Create some note sheets
        notesPanel.createNoteSheet("Sheet 1");
        notesPanel.createNoteSheet("Sheet 2");
        notesPanel.createNoteSheet("Sheet 3");

        // Ensure the initial selected note sheet is the first one created
        assertEquals("Sheet 1", notesPanel.getSelectedNoteSheet());

        // Select another note sheet
        notesPanel.selectNoteSheet("Sheet 3");
        assertEquals("Sheet 3", notesPanel.getSelectedNoteSheet());

        // Select a non-existing note sheet (should not change the selection)
        notesPanel.updateNoteSheet();
        //assertEquals("Sheet 3", notesPanel.getSelectedNoteSheet());
    }
    // Other test methods for additional functionalities...
}
