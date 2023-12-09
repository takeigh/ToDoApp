package tests;

import com.zynozin.ContentDataCommands;
import com.zynozin.ContentDataLabel;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ContentDataLabelTest {

    @Test
    void testSetDueDate() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        ContentDataLabel contentDataLabel = new ContentDataLabel();
        LocalDate dueDate = LocalDate.now();

        // Act
        contentDataLabel.setDueDate(dueDate);

        // Assert
        assertEquals(dueDate, getPrivateField(contentDataLabel, "dueDate"));
    }


    private <T> T getPrivateField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(object);
    }
}