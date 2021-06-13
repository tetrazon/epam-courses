package by.training.demo.service.sorting;

import by.training.demo.entity.Array;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *class with certain kind of sort must implement that interface
 */
public interface ArraySorting {

    Logger logger = LogManager.getLogger(ArraySorting.class);

    /**
     * @param array array to sort
     * @param isAscending true if ascending
     */
    void sort(Array array, boolean isAscending);
}
