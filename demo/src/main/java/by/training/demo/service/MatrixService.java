package by.training.demo.service;

import by.training.demo.entity.Matrix;
import by.training.demo.exception.DaoException;
import by.training.demo.exception.ServiceException;

import java.util.Optional;

public interface MatrixService {
    int create(int[][] intMatrix);
    int createRandom(String stringColumns,
                     String stringRows,
                     String stringMinValue,
                     String stringMaxValue) throws ServiceException;
    int createFromFile(String fileName) throws ServiceException;
    String read(String index) throws ServiceException;
    String add(String indexA, String indexB) throws ServiceException;
    String multiply(String indexA, String indexB) throws ServiceException;
    String subtract(String indexA, String indexB) throws ServiceException;
    String transpose(String index) throws ServiceException, DaoException;

}
