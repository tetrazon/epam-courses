package by.training.task08.service.creator;

import by.training.task08.service.creator.exception.FileNameCreatorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileNameCreatorTest {
    private FileNameCreator fileNameCreator = new FileNameCreator();
    @DataProvider(name = "wrongDataForTestFileName")
    public Object[][] createWrongDataForTestFileName() {

        return new Object[][]{
                {""},{null}};
    }

    @Test(description = "negative scenario for createFileName", dataProvider = "wrongDataForTestFileName",
    expectedExceptions = FileNameCreatorException.class)
    public void testCreateFileName(String filename) {
        fileNameCreator.createFileName(filename);
    }
}