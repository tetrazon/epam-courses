package by.training.carservicebook.service;

import by.training.carservicebook.dao.CarDao;
import by.training.carservicebook.dao.UserDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CarServiceImpl extends ServiceImpl implements CarService{
    @Override
    public List<Car> getCarByUserId(Integer id) throws ServiceException {
        CarDao  dao;
        try {
            dao = transaction.createDao(CarDao.class);
            return dao.getCarByUserId(id);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteCar(Car car) throws ServiceException {
        CarDao  dao;
        try {
            dao = transaction.createDao(CarDao.class);
            return dao.delete(car);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public Car getCarById(Integer id) throws ServiceException {
        CarDao  dao;
        try {
            dao = transaction.createDao(CarDao.class);
            return dao.findById(id);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer save(Car car) throws ServiceException {
        CarDao  dao;
        Integer carId;
        try {
            dao = transaction.createDao(CarDao.class);
            if(car.getId() == null){
                carId = dao.create(car);
            } else {
                dao.update(car);
                carId = car.getId();
            }
            return carId;
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }
}
