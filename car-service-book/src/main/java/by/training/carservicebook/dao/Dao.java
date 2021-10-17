package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao <T extends Entity> {
    List<T> findAll() throws DaoException;
    T findById(Integer id) throws DaoException;
    boolean delete(T t) throws DaoException;
    boolean delete(Integer id) throws DaoException;
    Integer create(T t) throws DaoException;
    T update(T t) throws DaoException;
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // log
        }
    }
    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close(); // or connection return code to the pool
            }
        } catch (SQLException e) {
            // log
        }
    }
}
