package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.CarRecord;

import java.sql.Date;
import java.util.List;

public interface CarRecordDao extends Dao<CarRecord> {
    List<CarRecord> findCarRecordHistoryByCarId(Integer carId) throws DaoException;
    List<CarRecord> findCarRecordByCarId(Integer carId) throws DaoException;

    Integer addToHistory(CarRecord carRecord) throws DaoException;
}
