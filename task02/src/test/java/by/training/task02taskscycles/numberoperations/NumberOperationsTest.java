package by.training.task02taskscycles.numberoperations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberOperationsTest {
    @DataProvider(name = "dataForGetSumSquare")
    public Object[][] getDataForGetSumSquare(){
        return new Object[][]{
                {new int[]{1, 3}, 14},
                {new int[]{-1, 4}, 31}
        };
    }
    @DataProvider(name = "wrongDataForGetSumSquare")
    public Object[][] getWrongDataForGetSumSquare(){
        return new Object[][]{
                {7, 3},
                {8, 4}
        };
    }

    @DataProvider(name = "dataForGetFormulaResult")
    public Object[][] getDataForGetFormulaResult(){
        return new Object[][]{
                new Object[]{1., 3, 6.},
        new Object[]{1., 4, 24}
        };
    }

    @DataProvider(name = "wrongDataForGetFormulaResult")
    public Object[][] getWrongDataForGetFormulaResult(){
        return new Object[][]{
                new Object[]{1.,-3},
                new Object[]{1., 0}
        };
    }

    @DataProvider(name = "dataForGetMaxDigitFromNumber")
    public Object[][] getDataForGetMaxDigitFromNumber(){
        return new Object[][]{
                {0, 0},
                {1, 1},
                {31, 3},
                {152, 5},
                {-54, 5}
        };
    }

    @DataProvider(name = "dataForGetFactorial")
    public Object[][] getDataForGetFactorial(){
        return new Object[][]{
                {0, 1},
                {1, 1},
                {3, 6},
                {8, 40320}
        };
    }


    @Test(description = "positive scenario for getSumSquare()",
            dataProvider = "dataForGetSumSquare")
    public void testGetSumSquare(int[] fromTo, int expected) {
       int actual = NumberOperations.getSumSquare(fromTo[0], fromTo[1]);
       assertEquals(actual, expected);
    }

    @Test(description = "negative scenario for getSumSquare()",
            dataProvider = "wrongDataForGetSumSquare",
    expectedExceptions = IllegalArgumentException.class,
    expectedExceptionsMessageRegExp = "\"from\" index must be greater than \"to\" index")
    public void testWrongGetSumSquare(int from, int to) {
         NumberOperations.getSumSquare(from, to);
    }

    @Test(description = "positive scenario for getFormulaResult()",
    dataProvider = "dataForGetFormulaResult")
    public void testGetFormulaResult(double a, int n, double expected) {
        double actual = NumberOperations.getFormulaResult(a, n);
        assertEquals(actual, expected, 0.000001);
    }

    @Test(description = "negative scenario for getFormulaResult()",
            dataProvider = "wrongDataForGetFormulaResult",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "n must be non negative")
    public void testWrongGetFormulaResult(double a, int n) {
        NumberOperations.getFormulaResult(a, n);
    }

    @Test(description = "positive scenario for getFactorial()",
    dataProvider = "dataForGetFactorial")
    public void testGetFactorial(int number, int expected) {
        int actual = NumberOperations.getFactorial(number);
        assertEquals(actual, expected);
    }

    @Test(description = "negative scenario for getFactorial()",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "number must be non negative")
    public void testWrongGetFactorial() {
        NumberOperations.getFactorial(-1);
    }



    @Test(description = "positive scenario for getMaxDigitFromNumber()",
    dataProvider = "dataForGetMaxDigitFromNumber")
    public void testGetMaxDigitFromNumber(int number, int expected) {
        int actual = NumberOperations.getMaxDigitFromNumber(number);
        assertEquals(actual, expected);
    }
}