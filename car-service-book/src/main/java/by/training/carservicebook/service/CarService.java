package by.training.carservicebook.service;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface CarService extends Service{
    List<Car> getCarByUserId(Integer id) throws ServiceException;
    boolean deleteCar(Car car) throws ServiceException;
    Car getCarById(Integer id) throws ServiceException;
    Integer save(Car car) throws ServiceException;
}
