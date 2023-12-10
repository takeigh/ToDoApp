package tests;
import org.junit.jupiter.api.Test;

import com.zynozin.ContentDataLabel;
import org.junit.*;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ContentDataLabelTest {

    @Test
    public void testSetDueDate() {
        // Arrange
        ContentDataLabel contentDataLabel = new ContentDataLabel();
        LocalDate dueDate = LocalDate.now();

        // Act
        contentDataLabel.setDueDate(dueDate);

        // Assert
        assertEquals(dueDate.toString(), contentDataLabel.dueDateLabel.getText().substring(5));
    }
}