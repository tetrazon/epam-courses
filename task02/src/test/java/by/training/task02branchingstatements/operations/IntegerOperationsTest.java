package by.training.task02branchingstatements.operations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IntegerOperationsTest {

    @DataProvider(name = "DataForComparing")
    public Object[][] getDataForComparing(){
        return new Object[][]{
                {new int[]{1, 1}, 8},
                {new int[]{2, 1}, 8},
                {new int[]{-1, 3}, 7},
                {new int[]{15, -1}, 8},
                {new int[]{-15, -1}, 7},
                {new int[]{-15, -16}, 8}
        };
    }

    @DataProvider(name = "DataForSetAllBiggerOrZero")
    public Object[][] getDataForSetAllBiggerOrZero(){
        return new Object[][]{
                {new int[]{1, 1}, new int[]{0, 0}},
                {new int[]{-1, 3}, new int[]{3, 3}},
                {new int[]{3, 1}, new int[]{3, 3}},
                {new int[]{1, -3}, new int[]{1, 1}},
                {new int[]{0, 0}, new int[]{0, 0}},
                {new int[]{-5, -8}, new int[]{-5, -5}},
        };
    }

    @Test(description = "positive scenario of comparing two integers",
    dataProvider = "DataForComparing")
    public void testCompareTwoInts(int[]ab, int expected) {
        int actual = IntegerOperations.compareTwoInts(ab[0], ab[1]);
        assertTrue(actual == expected);
    }

    @Test(description = "positive scenario for setAllBiggerOrZero()",
    dataProvider = "DataForSetAllBiggerOrZero")
    public void testSetAllBiggerOrZero(int actual[], int expected[]) {
        IntegerOperations.setAllBiggerOrZero(actual);
        assertEquals(actual, expected);
    }
}