package by.training.carservicebook.service;

import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface CarRecordService extends Service{
    List<CarRecord> findCarRecordByCarId(Integer id) throws ServiceException;
    boolean deleteById(Integer id) throws ServiceException;
    Integer save(CarRecord carRecord) throws ServiceException;
    CarRecord findById(Integer id) throws ServiceException;
}
