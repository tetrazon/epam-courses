package by.training.demo.service.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.ArraySorting;

/**
 * Shell sort implementation for Array
 * Shell sort is improved insertion sort
 * At first elements are sorted at a step,
 * starting from step = array.length / 2 to 1
 */
public final class ShellSort implements ArraySorting {
    /**
     * @param array array to sort
     * @param isAscending true if ascending
     */
    @Override
    public void sort(Array array, boolean isAscending) {
        int length = array.getLength();
        int sortCase = isAscending ? 1 : -1;

        //starting sort with step = length / 2
        for (int step = length / 2; step > 0; step /= 2) {
            for (int i = step; i < length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (array.compare(array.getValue(j), array.getValue(j + step)) == sortCase) {
                        array.swap(j, j + step);
                    }

                }
            }
        }
    }
}
