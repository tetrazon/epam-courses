package by.training.task05.parser;

import by.training.task05.entity.Triangle;
import by.training.task05.service.parser.TriangleParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TriangleParserTest {
    @DataProvider(name = "dataForTestParseTrianglesFromFile")
    public Object[][] createDataForTestParseTrianglesFromFile() {

        return new Object[][]{
                {"textData/triangles.txt"}
        };
    }

    @DataProvider(name = "dataForTestWrongParseTrianglesFromFile")
    public Object[][] createWrongDataForTestParseTrianglesFromFile() {

        return new Object[][]{
                {"textData/all-incorrect-triangles.txt"}
        };
    }
    @Test(description = "positive scenario of parseTrianglesFromFile()",
    dataProvider = "dataForTestParseTrianglesFromFile")
    public void testParseTrianglesFromFile(String filename) {
        List<Triangle> triangleList = TriangleParser.getInstance().parseTrianglesFromFile(filename);
        assertFalse(triangleList.isEmpty());
    }

    @Test(description = "negative scenario of parseTrianglesFromFile()",
            dataProvider = "dataForTestWrongParseTrianglesFromFile")
    public void testWrongParseTrianglesFromFile(String filename) {
        List<Triangle> triangleList = TriangleParser.getInstance().parseTrianglesFromFile(filename);
        assertTrue(triangleList.isEmpty());
    }
}