package by.training.demo.service.sorting.impl;

import by.training.demo.entity.Array;
import by.training.demo.service.sorting.ArraySorting;

/**
 * shake sort implementation
 */
public final class ShakeSort implements ArraySorting {
    @Override
    public void sort(Array array, boolean isAscending) {

        int sortCase = isAscending ? 1 : -1;
        int arrayLength = array.getLength();

        //unsorted bounds
        int lowerBound = 1;
        int upperBound = arrayLength - 1;
        int tmpBound = arrayLength - 1;


        //doing sort in two directions:
        //from bottom to up and from up to bottom
        do {
            //going from bottom to up
            for (int i = upperBound; i > 0 ; i--) {

                //if elements are not in proper position, swap it
                //and update the tmp bound
                if (array.compare(array.getValue(i - 1), array.getValue(i)) == sortCase) {
                    array.swap(i, i - 1);
                    tmpBound = i;
                }
            }

            //assuming that all elements below the lower bottom
            //have already sorted
            lowerBound = tmpBound + 1;

            //going from up to bottom
            for (int i = 1; i <= upperBound; i++) {

                //if elements are not in proper position, swap it
                //and update the tmp bound
                if (array.compare(array.getValue(i - 1), array.getValue(i)) == sortCase) {
                    array.swap(i, i - 1);
                    tmpBound = i;
                }
            }

            //assuming that all elements upper the upper bottom
            //have already sorted
            upperBound = tmpBound - 1;
        } while (lowerBound <= upperBound);


    }
}
