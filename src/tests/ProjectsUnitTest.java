package tests;

import com.zynozin.ProjectEntry;
import com.zynozin.ProjectPanel;
import org.junit.jupiter.api.*;

import java.io.*;

public class ProjectsUnitTest {

    // Test the singleton aspect of the Project Panel
    @Test
    public void testCreateProjectPanel() throws IOException {
        // First instantiation
        ProjectPanel panel = ProjectPanel.getProjectPanel();

        // Second instantiation
        ProjectPanel panel2 = ProjectPanel.getProjectPanel();

        // Assert
        Assertions.assertEquals(panel, panel2);
    }

    // Test reading from the file
    @Test
    public void testFileReading() throws IOException {
        // Set up
        ProjectEntry entry = new ProjectEntry();
        entry.titleField.setText("Title");
        entry.descriptionField.setText("Description");

        ProjectPanel panel = ProjectPanel.getProjectPanel();

        ProjectPanel.lastIdeaListSave.clear();
        ProjectPanel.lastIdeaListSave.add(entry);

        panel.saveProjects();

        ProjectPanel.lastIdeaListSave.clear();

        // Expected Output
        String output = "Title,Description";

        // Actual Output
        panel.writeSavedElements();
        String realOut = ProjectPanel.lastIdeaListSave.get(0).titleField.getText() + "," + ProjectPanel.lastIdeaListSave.get(0).descriptionField.getText();

        // Assert
        Assertions.assertEquals(output, realOut);
    }

    // Test writing to the file
    @Test
    public void testFileWriting() throws IOException {
        // Set up control
        ProjectEntry entry = new ProjectEntry();
        entry.titleField.setText("Title");
        entry.descriptionField.setText("Description");

        ProjectPanel panel = ProjectPanel.getProjectPanel();

        ProjectPanel.lastIdeaListSave.clear();
        ProjectPanel.lastIdeaListSave.add(entry);

        panel.saveProjects();

        // Expected Output
        String output = "Title,Description";

        // Actual output
        BufferedReader reader = new BufferedReader(new FileReader("files/ideas.txt"));
        String realOut = reader.readLine();

        // Assert
        Assertions.assertEquals(output, realOut);

        reader.close();
    }
}
