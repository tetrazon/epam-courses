package by.training.demo.service.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.ArraySorting;

/**
 * bubble sort implementation
 */
public final class BubbleSort implements ArraySorting {

    /**
     * @param array array to sort
     * @param isAscending true if ascending order, otherwise false
     */
    public void sort(Array array, boolean isAscending) {

        int length = array.getLength();
        int sortCase = isAscending ? 1 : -1;

        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > i; j--) {

                // if previous greater/less, swap it
                if (array.compare(array.getValue(j - 1), array.getValue(j)) == sortCase) {
                    array.swap(j, j - 1);
                }

            }
        }
    }

}
