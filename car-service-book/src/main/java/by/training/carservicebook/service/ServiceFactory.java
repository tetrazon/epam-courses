package by.training.carservicebook.service;


import by.training.carservicebook.service.exception.ServiceException;

public interface ServiceFactory {
    <T extends Service> T getService(Class<T> key) throws ServiceException;
    void close();
}