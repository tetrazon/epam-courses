package by.training.demo.service.sorting.impl;

import by.training.demo.service.sorting.ArraySorting;
import by.training.demo.entity.Array;

/**
 * selection sort implementation
 */
public final class SelectionSort implements ArraySorting {
    @Override
    public void sort(Array array, boolean isAscending) {

        int sortCase = isAscending ? -1 : 1;
        int arrayLength = array.getLength();

        int currentIndex;

        for (int i = 0; i < arrayLength - 1; i++) {

            //chose current index
            currentIndex = i;

            //starting compare from i + 1 element,
            //assuming that elements before i
            //have already sorted
            for (int j = i + 1; j < arrayLength; j++) {

                //compare if element with current index
                //satisfies the sort case
                if (array.compare(array.getValue(j), array.getValue(currentIndex)) == sortCase) {
                    currentIndex = j;
                }

            }

            //if current index does not equal itself,
            //swap it
            if (currentIndex != i) {
                array.swap(i, currentIndex);
            }
        }
    }
}
