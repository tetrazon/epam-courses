package by.training.demo.action.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.impl.SelectionSort;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SelectionSortTest {

    @Test(description = "testing ascending selection sort")
    public void testAscSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {1, 3, 4, 5, 7, 20, 44, 55, 78});

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(array, true);
        assertEquals(array, expected);
    }

    @Test(description = "testing descending selection sort")
    public void testDescSort() {
        Array<Integer> array = new Array(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array(new Integer[] {78, 55, 44, 20, 7, 5, 4, 3, 1});

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(array, false);
        assertEquals(array, expected);
    }
}