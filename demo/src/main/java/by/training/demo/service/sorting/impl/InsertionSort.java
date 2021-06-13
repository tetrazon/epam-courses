package by.training.demo.service.sorting.impl;

import by.training.demo.service.sorting.ArraySorting;
import by.training.demo.entity.Array;

/**
 * insertion sort implementation
 */
public final class InsertionSort implements ArraySorting {

    @Override
    public void sort(Array array, boolean isAscending) {

        int sortCase = isAscending ? 1 : -1;
        int arrayLength = array.getLength();
        Number currentValue;

        for (int i = 1; i < arrayLength; ++i) {
            currentValue = array.getValue(i);
            int j = i - 1;

            // Move elements of array[0..i-1], that are
            // greater/less than currentValue, to one position ahead
            // of their current position
            while (j >= 0 && array.compare(array.getValue(j), currentValue) == sortCase) {
                array.swap(j, j + 1);
                j--;
            }
            array.setValue(currentValue, j + 1);
        }
    }
}
