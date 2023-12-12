package edu.hw10;

import edu.hw10.TestClasses.TestClass;
import edu.hw10.TestClasses.TestRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void classTest() {
        // Arrange
        var rog = new RandomObjectGenerator();
        TestClass result1 = null;
        TestClass result2 = null;

        // Act
        result1 = (TestClass) rog.nextObject(TestClass.class, "create");
        result2 = (TestClass) rog.nextObject(TestClass.class, null);

        // Assert
        assertNotNull(result1);
        assertNotNull(result2);
    }

    @Test
    void recordTest() {
        // Arrange
        var rog = new RandomObjectGenerator();
        TestRecord result = null;

        // Act
        result = (TestRecord) rog.nextObject(TestRecord.class, null);

        // Assert
        assertNotNull(result);
    }

    @Test
    void illegalFabricMethodTest() {
        // Arrange
        var rog = new RandomObjectGenerator();
        Executable exec = () -> rog.nextObject(TestRecord.class, "generateRecord");

        // Assert
        assertThrows(RuntimeException.class, exec);
    }
}
