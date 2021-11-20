package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.CarRecord;

import java.sql.Date;
import java.util.List;

public interface CarRecordDao extends Dao<CarRecord> {
    List<CarRecord> findCarRecordIsOnTender();
    List<CarRecord> findCarRecordHistoryByCarId(Integer carId);
    List<CarRecord> findCarRecordByCarId(Integer carId) throws DaoException;
}
