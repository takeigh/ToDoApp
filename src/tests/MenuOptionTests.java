package tests;

import com.zynozin.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseEvent;
import java.io.IOException;

import static org.junit.Assert.*;


public class MenuOptionTests {

    @Before
    public void setUp() throws IOException {
        // Initialize necessary components before each test
        MyFrame mainFrame = new MyFrame();
    }

    private boolean isOnlyPanelVisible(String panelName) {
        switch (panelName) {
            case "Tasks List":
                return MainContent.tasksContentScrollPane.isVisible();
            case "Notes":
                return MainContent.notesPanel.isVisible();
            case "Project Ideas":
                return MainContent.ideaScrollPane.isVisible() && MainContent.ideaPanel.isVisible();
            case "Reading Journal":
                return MainContent.bookScrollPane.isVisible();
            case "Calendar":
                return MainContent.calendarPanel.isVisible();
            case "Grocery List":
                return MainContent.groceryScrollPane.isVisible();
            case "Wishlist":
                return MainContent.wishlistScrollPane.isVisible() && MainContent.wishlistPanel.isVisible();
            default:
                return false;
        }
    }
    @Test
    public void testTaskListClick() {
        // Create a MenuOption instance
        MenuOption menuOption = new MenuOption("Tasks List");

        // Verify the initial state
        assertTrue(MainContent.tasksContentScrollPane.isVisible());

        // Simulate a mouse click event
        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        // Assert the overall logic
        assertEquals("Tasks List.", MainContent.mainContent.getContentHeader().getTitleText());
        assertTrue(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testNotesPanelClick() {
        MenuOption menuOption = new MenuOption("Notes");

        // Verify the initial state
        assertTrue(MainContent.notesPanel.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertTrue(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testProjectIdeasClick() {
        MenuOption menuOption = new MenuOption("Project Ideas");

        // Verify the initial state
        assertTrue(MainContent.ideaPanel.isVisible());
        assertTrue(MainContent.ideaScrollPane.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertTrue(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testReadingJournalClick() {
        MenuOption menuOption = new MenuOption("Reading Journal");

        // Verify the initial state
        assertTrue(MainContent.bookScrollPane.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertTrue(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testCalendarClick() {
        MenuOption menuOption = new MenuOption("Calendar");

        // Verify the initial state
        assertTrue(MainContent.calendarPanel.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertTrue(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testGroceryListClick() {
        MenuOption menuOption = new MenuOption("Grocery List");

        // Verify the initial state
        assertTrue(MainContent.groceryScrollPane.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertTrue(isOnlyPanelVisible("Grocery List"));
        assertFalse(isOnlyPanelVisible("wishlist"));

        ContentDataPanel.lastTasksSave.clear();
    }
    @Test
    public void testWishlistClick() {
        MenuOption menuOption = new MenuOption("Wishlist");

        // Verify the initial state
        assertTrue(MainContent.wishlistScrollPane.isVisible());
        assertTrue(MainContent.wishlistPanel.isVisible());

        MouseEvent clickEvent = new MouseEvent(menuOption, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(),
                0, 0, 0, 0, false);
        menuOption.mousePressed(clickEvent);

        assertFalse(isOnlyPanelVisible("Tasks List"));
        assertFalse(isOnlyPanelVisible("Notes"));
        assertFalse(isOnlyPanelVisible("Project Ideas"));
        assertFalse(isOnlyPanelVisible("Reading Journal"));
        assertFalse(isOnlyPanelVisible("Calendar"));
        assertFalse(isOnlyPanelVisible("Grocery List"));
        assertTrue(isOnlyPanelVisible("Wishlist"));
    }
}
