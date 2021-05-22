package by.training.task01linearalgorithm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.training.task01linearalgorithm.CharacterPrograms.*;
import static org.testng.Assert.*;

public class CharacterProgramsTest {

    @DataProvider(name = "dataForGetCharSerialNumber")
    public Object[][] createDataForGetCharSerialNumber() {
        return new Object[][]{
                {'a', 97},
                {'b', 98},
                {'c', 99},
                {'d', 100}
        };
    }

    @DataProvider(name = "dataForGetPreviousCharacter")
    public Object[][] createDataForGetPreviousCharacter() {
        return new Object[][]{
                {'b', 'a'},
                {'A', '@'},
                {'.', '-'},
        };
    }

    @DataProvider(name = "dataForGetNextCharacter")
    public Object[][] createDataForGetNextCharacter() {
        return new Object[][]{
                {'K', 'L'},
                {'#', '$'},
                {'5', '6'},
        };
    }

    @Test(description = "Positive scenario of the getCharSerialNumber", dataProvider = "dataForGetCharSerialNumber")
    public void testGetCharSerialNumber(char ch, int result) {
        assertTrue(result == getCharSerialNumber(ch));
    }

    @Test(description = "Positive scenario of the getPreviousCharacter", dataProvider = "dataForGetPreviousCharacter")
    public void testGetPreviousCharacter(char ch, char result) {
        assertTrue(result == getPreviousCharacter(ch));
    }

    @Test(description = "Positive scenario of the getNextCharacter", dataProvider = "dataForGetNextCharacter")
    public void testGetNextCharacter(char ch, char result) {
        assertTrue(result == getNextCharacter(ch));
    }
}