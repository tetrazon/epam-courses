package by.training.demo.service.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.ArraySorting;

/**
 * merge sort implementation
 */
public final class MergeSort implements ArraySorting {
    /**
     * @param array       array to sort
     * @param isAscending true if ascending
     */
    @Override
    public void sort(Array array, boolean isAscending) {
        mergeSort(array, 0, array.getLength() - 1, isAscending);
    }

    /**
     * sort array splitting by 2 recursively until
     * size of array more than 2
     * @param array
     * @param low
     * @param high
     * @param isAscending
     */
    private void mergeSort(Array array, int low, int high, boolean isAscending) {
        if (high <= low) return;

        int mid = (low+high)/2;
        mergeSort(array, low, mid, isAscending);
        mergeSort(array, mid+1, high, isAscending);
        merge(array, low, mid, high, isAscending);
    }

    /**
     * create and merge temp arrays into result array
     * @param array result array
     * @param low start index of the temp array
     * @param mid the tmp middle
     * @param high end index of the temp array
     * @param isAscending true if ascending mode
     */
    private void merge(Array array, int low, int mid, int high, boolean isAscending) {
        // Creating temporary subarrays
        Array<Number> leftArray = new Array<>(new Number[mid - low + 1]);
        Array<Number> rightArray = new Array<>(new Number[high - mid]);

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.getLength(); i++){
            leftArray.setValue(array.getValue(low + i), i);
        }

        for (int i = 0; i < rightArray.getLength(); i++){
            rightArray.setValue(array.getValue(mid + i + 1), i);
        }

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        int sortCase = isAscending ? -1 : 1;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {

            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.getLength() && rightIndex < rightArray.getLength()) {

                if (leftArray.compare(leftArray.getValue(leftIndex), rightArray.getValue(rightIndex)) == sortCase) {

                    array.setValue(leftArray.getValue(leftIndex), i);
                    leftIndex++;

                } else {

                    array.setValue(rightArray.getValue(rightIndex), i);
                    rightIndex++;

                }
            } else if (leftIndex < leftArray.getLength()) {

                // If all elements have been copied from rightArray, copy rest of leftArray
                array.setValue(leftArray.getValue(leftIndex), i);
                leftIndex++;

            } else if (rightIndex < rightArray.getLength()) {

                // If all elements have been copied from leftArray, copy rest of rightArray
                array.setValue(rightArray.getValue(rightIndex), i);
                rightIndex++;

            }
        }
    }
}
