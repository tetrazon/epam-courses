package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    List<User> findUserByRole(Role role) throws DaoException;
    User read(String login, String password) throws DaoException;
}
