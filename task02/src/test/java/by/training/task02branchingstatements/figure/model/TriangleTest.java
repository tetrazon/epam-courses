package by.training.task02branchingstatements.figure.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TriangleTest {

    @DataProvider(name = "wrongDataForTriangleCreation")
    public Object[][] getWrongDataForTriangleCreation(){
        return new Object[][]{
                {0, 1, 2},
                {2, 0, 21},
                {77, 78, 0},
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}

        };
    }

    @DataProvider(name = "dataForTriangleCreation")
    public Object[][] getDataForTriangleCreation(){
        return new Object[][]{
                {1, 1, 1},
                {2, 2, 2},
                {77, 77, 77}

        };
    }

    @Test(description = "negative scenario for triangle object creation",
            dataProvider = "wrongDataForTriangleCreation",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "The triangle sides must be positive")
    public void testWrongTriangleCreation(int a, int b, int c){
        Triangle triangle = new Triangle(a, b, c);
    }

    @Test(description = "positive scenario for triangle object creation",
            dataProvider = "dataForTriangleCreation")
    public void testTriangleCreation(int a, int b, int c){

        Triangle triangle = new Triangle(a, b, c);
        assertTrue(triangle.getA() == a && triangle.getB() == b && triangle.getC() == c);
    }


}