package by.training.demo.action.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.impl.InsertionSort;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InsertionSortTest {

    @Test(description = "testing ascending insertion sort")
    public void testAscSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {1, 3, 4, 5, 7, 20, 44, 55, 78});

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(array, true);
        assertEquals(array, expected);
    }

    @Test(description = "testing descending insertion sort")
    public void testDescSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {78, 55, 44, 20, 7, 5, 4, 3, 1});

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(array, false);
        assertEquals(array, expected);
    }
}