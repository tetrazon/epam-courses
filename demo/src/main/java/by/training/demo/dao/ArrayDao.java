package by.training.demo.dao;

import by.training.demo.entity.Array;
import by.training.demo.exception.DaoException;

import java.io.File;
import java.util.Optional;

public interface ArrayDao {
    int create(Array array);
    int createIntegerFromFile(String fileName) throws DaoException;
    int createDoubleFromFile(String fileName) throws DaoException;
    Optional<Array> read(int index) throws DaoException;
    void delete(int index) throws DaoException;
}
