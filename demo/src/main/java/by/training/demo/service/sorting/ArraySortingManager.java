package by.training.demo.service.sorting;

import by.training.demo.service.sorting.impl.*;

public enum ArraySortingManager {
    BUBBLE(new BubbleSort()),
    INSERTION(new InsertionSort()),
    MERGE(new MergeSort()),
    SELECTION(new SelectionSort()),
    SHAKE(new ShakeSort()),
    SHELL(new ShellSort());

    private final ArraySorting sorting;

    private ArraySortingManager(ArraySorting sorting) {
        this.sorting = sorting;
    }

}
