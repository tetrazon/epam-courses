package by.training.task01linearalgorithm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.training.task01linearalgorithm.LinearPrograms.*;
import static org.testng.Assert.*;

public class LinearProgramsTest {

    @DataProvider(name = "dataForCalcSum")
    public Object[][] createDataForCalcSum() {
        return new Object[][]{
                {new double[]{3, 5}, 8},
                {new double[]{-3, 3}, 0},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, -8},
                {new double[]{-300, 200}, -100},
                {new double[]{10, 0}, 10}};
    }

    @DataProvider(name = "dataForCalcDifference")
    public Object[][] createDataForCalcDifference() {
        return new Object[][]{
                {new double[]{3, 5}, -2},
                {new double[]{-3, 3}, -6},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, 2},
                {new double[]{-300, 200}, -500},
                {new double[]{10, 0}, 10}};
    }

    @DataProvider(name = "dataForCalcMultiply")
    public Object[][] createDataForCalcMultiply() {
        return new Object[][]{
                {new double[]{3, 5}, 15},
                {new double[]{-3, 3}, -9},
                {new double[]{0, 0}, 0},
                {new double[]{-3, -5}, 15},
                {new double[]{-300, 200}, -60000},
                {new double[]{10, 0}, 0}};
    }

    @DataProvider(name = "dataForCalcDivision")
    public Object[][] createDataForCalcDivision() {
        return new Object[][]{
                {new double[]{3, 1}, 3},
                {new double[]{-3, 3}, -1},
                {new double[]{-10, -5}, 2},
                {new double[]{-300, 200}, -1.5},
                {new double[]{0, 1}, 0}};
    }

    @DataProvider(name = "dataForCalcFormula9Result")
    public Object[][] createDataForCalcFormula9Result() {
        return new Object[][]{
                {new double[]{3, 1, 2, 2}, 0.5},
                {new double[]{-1, 1, 1, -1}, -1},
                {new double[]{4, 13, 11, -10}, -0.1},
                {new double[]{0, 0, 141, -20}, -0.05},
                {new double[]{24.5, 0, 15.5, 5}, 0.2},
                {new double[]{0, 13, 15.5, -5}, -0.2},
        };
    }

    @DataProvider(name = "wrongDataForCalcFormula9Result")
    public Object[][] createWrongDataForCalcFormula9Result() {
        return new Object[][]{
                {new double[]{3, 1, 0, 2}},
                {new double[]{-1, 1, 1, 0}},
                {new double[]{4, 13, 0, 0}},
                {new double[]{0, 0, 0, 0}},
                {new double[]{0, 12, 0, 5}},
                {new double[]{13.6, 44.8, 78.2, 0}},
        };
    }

    @DataProvider(name = "dataForCalcCubeAverage")
    public Object[][] createDataForCalcCubeAverage() {
        return new Object[][]{
                {new double[]{1, 1}, 1},
                {new double[]{-3, 3}, 0},
                {new double[]{10, -10}, 0},
                {new double[]{0, 0}, 0},
                {new double[]{0, 1}, 0.5},
                {new double[]{1, 0}, 0.5}};
    }

    @DataProvider(name = "dataForCalcAbsGeometricMean")
    public Object[][] createDataForCalcAbsGeometricMean() {
        return new Object[][]{
                {new double[]{1, 1}, 1},
                {new double[]{-3, 3}, 3},
                {new double[]{10, -10}, 10},
                {new double[]{32, 2}, 8},
                {new double[]{0, 1}, 0},
                {new double[]{1, 0}, 0}};
    }

    @DataProvider(name = "dataForCalcQuadraticEquationResults")
    public Object[][] createDataForCalcQuadraticEquationResults() {
        return new Object[][]{
                {new double[]{1, 2, -15}, new double[]{3, -5}},
                {new double[]{1, -4, -12}, new double[]{6, -2}}};
    }

    @Test(description = "Positive scenario of the calcSum", dataProvider = "dataForCalcSum")
    public void testCalcSum(double ab[],  double c) {
        double actual = calcSum(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);

    }

    @Test(description = "Positive scenario of the calcDifference", dataProvider = "dataForCalcDifference")
    public void testCalcDifference(double ab[],  double c) {
        double actual = calcDifference(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the calcMultiply", dataProvider = "dataForCalcMultiply")
    public void testCalcMultiply(double ab[],  double c) {
        double actual = calcMultiply(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

   @Test(description = "Positive scenario of the calcDivision",
            dataProvider = "dataForCalcDivision")
    public void testCalcDivision(double ab[],  double c) {
        double actual = calcDivision(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Testing division by zero",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "you cannot divide by zero")
    public void testCalcDivisionByZero() {
        calcDivision(1,0);
    }

    @Test(description = "Positive scenario of the calcFormula9Result",
            dataProvider = "dataForCalcFormula9Result")
    public void testCalcFormula9Result(double abcd[],  double result) {
        double actual = calcFormula9Result(abcd[0],abcd[1],abcd[2], abcd[3]);
        double expected = result;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Negative scenario of the calcFormula9Result",
            dataProvider = "wrongDataForCalcFormula9Result",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "you cannot divide by zero")
    public void testWrongCalcFormula9Result(double abcd[]) {
        calcFormula9Result(abcd[0],abcd[1],abcd[2], abcd[3]);
    }

    @Test(description = "Positive scenario of the calcCubeAverage",
            dataProvider = "dataForCalcCubeAverage")
    public void testCalcCubeAverage(double ab[],  double c) {
        double actual = calcCubeAverage(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the calcAbsGeometricMean",
            dataProvider = "dataForCalcAbsGeometricMean")
    public void testCalcAbsGeometricMean(double ab[],  double c) {
        double actual = calcAbsGeometricMean(ab[0],ab[1]);
        double expected = c;
        assertEquals(actual, expected, 0.0001);
    }

    @Test(description = "Positive scenario of the calcQuadraticEquationResults",
            dataProvider = "dataForCalcQuadraticEquationResults")
    public void testCalcQuadraticEquationResults(double abc[],  double []result) {
        double[] actualArray = calcQuadraticEquationResults(abc[0], abc[1], abc[2]);

        assertEquals(actualArray, result, 0.0001);
    }

    @Test(description = "Negative scenario of the calcQuadraticEquationResults, a = 0",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "the method requires a != 0")
    public void testZeroACalcQuadraticEquationResults() {
        calcQuadraticEquationResults(0, 1, 2);
    }

}