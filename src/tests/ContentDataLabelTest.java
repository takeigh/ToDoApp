package tests;

import com.zynozin.ContentDataLabel;
import org.junit.*;
import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ContentDataLabelTest {

    @Test
    public void testSetDueDate() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        ContentDataLabel contentDataLabel = new ContentDataLabel();
        LocalDate dueDate = LocalDate.now();

        // Act
        contentDataLabel.setDueDate(dueDate);

        // Assert
        assertEquals(dueDate.toString(), contentDataLabel.dueDateLabel.getText().substring(5));
    }
}