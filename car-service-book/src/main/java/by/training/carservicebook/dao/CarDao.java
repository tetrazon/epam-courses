package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;

import java.util.List;

public interface CarDao extends Dao<Car>{
    List<Car> getCarByUserId(Integer id) throws DaoException;
    int deleteCarByUserId(Integer id);
}
