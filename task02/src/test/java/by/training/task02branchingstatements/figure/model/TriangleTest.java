package by.training.task02branchingstatements.figure.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @Test(description = "negative scenario for triangle object creation",
            dataProvider = "wrongDataForTriangleCreation",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "The triangle sides must be positive")
    public void testWrongTriangleCreation(int a, int b, int c){
        Triangle triangle = new Triangle(a, b, c);
    }

}