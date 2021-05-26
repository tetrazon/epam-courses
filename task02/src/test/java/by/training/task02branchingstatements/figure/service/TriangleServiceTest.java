package by.training.task02branchingstatements.figure.service;

import by.training.task02branchingstatements.figure.model.Triangle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleServiceTest {

    @DataProvider(name = "dataForEquilateralTriangleCreation")
    public Object[][] getDataForEquilateralTriangleCreation(){
        return new Object[][]{
                {1, 1, 1},
                {15, 15, 15},
                {1000, 1000, 1000}
        };
    }

    @DataProvider(name = "wrongDataForEquilateralTriangleCreation")
    public Object[][] getWrongDataForEquilateralTriangleCreation(){
        return new Object[][]{
                {1, 1, 2},
                {2, 4, 21},
                {77, 78, 5}
        };
    }

    @Test(description = "positive scenario of isEquilateral()",
            dataProvider = "dataForEquilateralTriangleCreation")
    public void testIsEquilateral(int a, int b, int c) {
        TriangleService triangleService = new TriangleService();
        Triangle triangle = new Triangle(a, b, c);
        assertTrue(triangleService.isEquilateral(triangle));
    }

    @Test(description = "negative scenario of isEquilateral()",
    dataProvider = "wrongDataForEquilateralTriangleCreation")
    public void testIsNotEquilateral(int a, int b, int c) {
        TriangleService triangleService = new TriangleService();
        Triangle triangle = new Triangle(a, b, c);
        assertFalse(triangleService.isEquilateral(triangle));
    }
}