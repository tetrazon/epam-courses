package by.training.carservicebook.service;

import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll() throws ServiceException;
    User findById(Integer id) throws ServiceException;
    User findByLoginAndPassword(String login, String password) throws ServiceException;
    void save(User user) throws ServiceException;
    void delete(Integer id) throws ServiceException;
}
