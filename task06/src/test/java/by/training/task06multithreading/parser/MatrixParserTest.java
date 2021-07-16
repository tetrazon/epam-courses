package by.training.task06multithreading.parser;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.parser.MatrixParser;
import by.training.task06multithreading.service.parser.exception.MatrixParserException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixParserTest {
    MatrixParser matrixParser = MatrixParser.getInstance();

    @DataProvider(name = "dataForParseMatrixFromFile")
    public Object[][] createDataForParseMatrixFromFile() {

        return new Object[][]{
                {"data/matrix.txt"}
        };
    }

    @DataProvider(name = "dataForTestWrongParseMatrixFromFile")
    public Object[][] createWrongDataForParseMatrixFromFile() {

        return new Object[][]{
                {"data/incorrect-file.txt"},
                {null},
                {""}
        };
    }

    @Test(description = "positive scenario of parseMatrixFromFile()",
    dataProvider = "dataForParseMatrixFromFile")
    public void testParseMatrixFromFile(String filename) throws MatrixParserException {
        Matrix matrix = matrixParser.parseMatrixFromFile(filename);
        assertNotNull(matrix);
    }

    @Test(description = "negative scenario of parseMatrixFromFile()",
            dataProvider = "dataForTestWrongParseMatrixFromFile",
            expectedExceptions = MatrixParserException.class)
    public void testWrongParseMatrixFromFile(String filename) throws MatrixParserException {
        matrixParser.parseMatrixFromFile(filename);
    }
}