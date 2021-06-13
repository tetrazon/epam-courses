package by.training.demo.action.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.impl.MergeSort;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MergeSortTest {

    @Test(description = "testing ascending merge sort")
    public void testAscSort() {
        Array<Integer> array = new Array<>(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array<>(new Integer[] {1, 3, 4, 5, 7, 20, 44, 55, 78});

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array, true);
        assertEquals(array, expected);
    }

    @Test(description = "testing descending merge sort")
    public void testDescSort() {
        Array<Integer> array = new Array<>(new Integer[]{5, 7, 20, 44, 4, 78, 1, 55, 3});
        Array<Integer> expected = new Array<>(new Integer[] {78, 55, 44, 20, 7, 5, 4, 3, 1});

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array, false);
        assertEquals(array, expected);
    }
}