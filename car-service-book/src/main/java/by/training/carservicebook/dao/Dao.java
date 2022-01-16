package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Entity;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao <T extends Entity> {
    List<T> findAll() throws DaoException;
    T findById(Integer id) throws DaoException;
    boolean delete(T t) throws DaoException;
    boolean delete(Integer id) throws DaoException;
    Integer create(T t) throws DaoException;
    void update(T t) throws DaoException;
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ignored) {
        }
    }
}
