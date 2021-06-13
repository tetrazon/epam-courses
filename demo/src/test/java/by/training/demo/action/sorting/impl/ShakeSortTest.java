package by.training.demo.action.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.impl.ShakeSort;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShakeSortTest {

    @Test(description = "testing ascending shake sort")
    public void testAscSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {1, 3, 4, 5, 7, 20, 44, 55, 78});

        ShakeSort shakeSort = new ShakeSort();
        shakeSort.sort(array, true);
        assertEquals(array, expected);
    }

    @Test(description = "testing descending shake sort")
    public void testDescSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {78, 55, 44, 20, 7, 5, 4, 3, 1});

        ShakeSort shakeSort = new ShakeSort();
        shakeSort.sort(array, false);
        assertEquals(array, expected);
    }
}