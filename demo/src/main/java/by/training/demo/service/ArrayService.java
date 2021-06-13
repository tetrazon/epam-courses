package by.training.demo.service;

import by.training.demo.exception.ServiceException;
import by.training.demo.service.sorting.ArraySorting;
import by.training.demo.entity.Array;

import java.util.Optional;

public interface ArrayService {
    int createInteger(String stringArray) throws ServiceException;
    int createDouble(String request) throws ServiceException;
    int createIntegerFromFile(String fileName) throws ServiceException;
    int createDoubleFromFile(String fileName) throws ServiceException;
    Optional<Array> read(String StringIndex) throws ServiceException;
    void delete (String stringIndex) throws ServiceException;
    String sort(ArraySorting arraySorting, String arrayIndex, boolean isAscending) throws ServiceException;
}
