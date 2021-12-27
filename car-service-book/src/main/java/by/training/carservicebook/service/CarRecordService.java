package by.training.carservicebook.service;

import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface CarRecordService extends Service{
    List<CarRecord> findCarRecordByCarId(Integer id) throws ServiceException;
    boolean delete(CarRecord carRecord) throws ServiceException;
    Integer save(CarRecord carRecord) throws ServiceException;
    CarRecord findById(Integer id) throws ServiceException;
    List <CarRecord> findByCarIdAndTender(Integer id, boolean isTender) throws ServiceException;
    List <CarRecord> findByOffers(List<Offer> offers) throws ServiceException;
    void markDone(CarRecord carRecord) throws ServiceException;
}
