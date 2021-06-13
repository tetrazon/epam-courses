package by.training.demo.dao;

import by.training.demo.entity.Matrix;
import by.training.demo.exception.DaoException;

import java.util.Optional;

public interface MatrixDao {
    int create(int[][] intMatrix);
    int createRandom(int columns, int rows, int minValue, int maxValue) throws DaoException;
    int createFromFile(String fileName) throws DaoException;
    Optional<Matrix> read(int index) throws DaoException;
    Matrix add(int indexA, int indexB) throws DaoException;
    Matrix multiply(int indexA, int indexB) throws DaoException;
    Matrix subtract(int indexA, int indexB) throws DaoException;
    Matrix transpose(int index) throws DaoException;
}
