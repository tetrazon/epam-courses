package by.training.demo.service.sorting;

import by.training.demo.controller.command.Command;
import by.training.demo.entity.CommandName;
import by.training.demo.entity.SortName;
import by.training.demo.service.sorting.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class ArraySortingProvider {
    private static final Logger logger = LogManager.getLogger(ArraySortingProvider.class);

    private final Map<SortName, ArraySorting> repository = new HashMap<>();

    public ArraySortingProvider(){
        repository.put(SortName.BUBBLE, new BubbleSort());
        repository.put(SortName.INSERTION, new InsertionSort());
        repository.put(SortName.MERGE, new MergeSort());
        repository.put(SortName.SELECTION, new SelectionSort());
        repository.put(SortName.SHAKE, new ShakeSort());
        repository.put(SortName.SHELL, new ShellSort());
    }

    public ArraySorting getSorting(String name) {
        SortName sortName;
        ArraySorting arraySorting;
        try {
            sortName = SortName.valueOf(name.toUpperCase());
            arraySorting = repository.get(sortName);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("wrong sort, bubble by default");
            arraySorting = repository.get(SortName.BUBBLE);
        }
        return arraySorting;
    }
}
