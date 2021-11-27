package by.training.carservicebook.service;

import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll(Integer except) throws ServiceException;
    User findById(Integer id) throws ServiceException;
    User findByLoginAndPassword(String login, String password) throws ServiceException;
    void save(User user) throws ServiceException;
    boolean delete(Integer id) throws ServiceException;
    void banUser(Integer userId, boolean isBanned) throws ServiceException;
}
