package by.training.task01linearalgorithm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.training.task01linearalgorithm.LinearPrograms.*;
import static org.testng.Assert.*;

public class LinearProgramsTest {

    @DataProvider(name = "dataForGetSum")
    public Object[][] createDataForGetSum() {
        return new Object[][]{
                {new double[]{3, 5}, 8},
                {new double[]{-3, 3}, 0},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, -8},
                {new double[]{-300, 200}, -100},
                {new double[]{10, 0}, 10}};
    }

    @DataProvider(name = "dataForGetDifference")
    public Object[][] createDataForGetDifference() {
        return new Object[][]{
                {new double[]{3, 5}, -2},
                {new double[]{-3, 3}, -6},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, 2},
                {new double[]{-300, 200}, -500},
                {new double[]{10, 0}, 10}};
    }

    @DataProvider(name = "dataForGetMultiply")
    public Object[][] createDataForGetMultiply() {
        return new Object[][]{
                {new double[]{3, 5}, 15},
                {new double[]{-3, 3}, -9},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, 15},
                {new double[]{-300, 200}, -60000},
                {new double[]{10, 0}, 0}};
    }

    @DataProvider(name = "dataForGetDivision")
    public Object[][] createDataForGetDivision() {
        return new Object[][]{
                {new double[]{3, 1}, 3},
                {new double[]{-3, 3}, -1},
                {new double[]{-10, -5}, 2},
                {new double[]{-300, 200}, -1.5},
                {new double[]{0, 1}, 0}};
    }

    @DataProvider(name = "dataForGetFormula9Result")
    public Object[][] createDataForGetFormula9Result() {
        return new Object[][]{
                {new double[]{3, 1, 2, 2}, 0.5},
                {new double[]{-1, 1, 1, -1}, -1},
                {new double[]{4, 13, 11, -10}, -0.1},
                {new double[]{0, 0, 141, -20}, -0.05},
                {new double[]{24.5, 0, 15.5, 5}, 0.2},
                {new double[]{0, 13, 15.5, -5}, -0.2},
        };
    }

    @DataProvider(name = "wrongDataForGetFormula9Result")
    public Object[][] createWrongDataForGetFormula9Result() {
        return new Object[][]{
                {new double[]{3, 1, 0, 2}},
                {new double[]{-1, 1, 1, 0}},
                {new double[]{4, 13, 0, 0}},
                {new double[]{0, 0, 0, 0}},
                {new double[]{0, 12, 0, 5}},
                {new double[]{13.6, 44.8, 78.2, 0}},
        };
    }

    @DataProvider(name = "dataForGetCubeAverage")
    public Object[][] createDataForGetCubeAverage() {
        return new Object[][]{
                {new double[]{1, 1}, 1},
                {new double[]{-3, 3}, 0},
                {new double[]{10, -10}, 0},
                {new double[]{0, 0}, 0},
                {new double[]{0, 1}, 0.5},
                {new double[]{1, 0}, 0.5}};
    }

    @DataProvider(name = "dataForGetAbsGeometricMean")
    public Object[][] createDataForGetAbsGeometricMean() {
        return new Object[][]{
                {new double[]{1, 1}, 1},
                {new double[]{-3, 3}, 3},
                {new double[]{10, -10}, 10},
                {new double[]{32, 2}, 8},
                {new double[]{0, 1}, 0},
                {new double[]{1, 0}, 0}};
    }

    @DataProvider(name = "dataForGetQuadraticEquationResults")
    public Object[][] createDataForGetQuadraticEquationResults() {
        return new Object[][]{
                {new double[]{1, 2, -15}, new double[]{3, -5}},
                {new double[]{1, -4, -12}, new double[]{6, -2}}};
    }

    @Test(description = "Positive scenario of the getSum", dataProvider = "dataForGetSum")
    public void testGetSum(double ab[],  double c) {
        double actual = getSum(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);

    }

    @Test(description = "Positive scenario of the getDifference", dataProvider = "dataForGetDifference")
    public void testGetDifference(double ab[],  double c) {
        double actual = getDifference(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the getMultiply", dataProvider = "dataForGetMultiply")
    public void testGetMultiply(double ab[],  double c) {
        double actual = getMultiply(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

   @Test(description = "Positive scenario of the getDivision",
            dataProvider = "dataForGetDivision")
    public void testGetDivision(double ab[],  double c) {
        double actual = getDivision(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Testing division by zero",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "you cannot divide by zero")
    public void testGetDivisionByZero() {
        getDivision(1,0);
    }

    @Test(description = "Positive scenario of the getFormula9Result",
            dataProvider = "dataForGetFormula9Result")
    public void testGetFormula9Result(double abcd[],  double result) {
        double actual = getFormula9Result(abcd[0],abcd[1],abcd[2], abcd[3]);
        double expected = result;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Negative scenario of the getFormula9Result",
            dataProvider = "wrongDataForGetFormula9Result",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "you cannot divide by zero")
    public void testWrongGetFormula9Result(double abcd[]) {
        getFormula9Result(abcd[0],abcd[1],abcd[2], abcd[3]);
    }

    @Test(description = "Positive scenario of the getCubeAverage",
            dataProvider = "dataForGetCubeAverage")
    public void testGetCubeAverage(double ab[],  double c) {
        double actual = getCubeAverage(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the getAbsGeometricMean",
            dataProvider = "dataForGetAbsGeometricMean")
    public void testGetAbsGeometricMean(double ab[],  double c) {
        double actual = getAbsGeometricMean(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the getQuadraticEquationResults",
            dataProvider = "dataForGetQuadraticEquationResults")
    public void testGetQuadraticEquationResults(double abc[],  double []result) {
        double[] actualArray = getQuadraticEquationResults(abc[0], abc[1], abc[2]);

        assertEquals(actualArray, result, 0.0001);
    }

    @Test(description = "Negative scenario of the getQuadraticEquationResults, a = 0",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "the method requires a != 0")
    public void testZeroAGetQuadraticEquationResults() {
        getQuadraticEquationResults(0, 1, 2);
    }

}