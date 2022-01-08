package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    List<User> findByRole(Role role) throws DaoException;
    User findByLoginAndPassword(String login, String password) throws DaoException;
    List<User> findAll(Integer except) throws DaoException;
    void banUser(Integer userId, boolean isBanned) throws DaoException;
    boolean userExistsByLogin(String login) throws DaoException;
    List<User> findByRoleAndDistrict(Role role, String district) throws DaoException;
    User findByLogin(String login) throws DaoException;
}
