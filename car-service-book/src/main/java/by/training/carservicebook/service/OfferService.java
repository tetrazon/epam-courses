package by.training.carservicebook.service;

import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface OfferService extends Service{
    List<Offer> getAll() throws ServiceException;
    List<Offer> getSelectedByMasterId(Integer masterId) throws ServiceException;
    void deleteByCarRecordId(Integer carRecordId) throws ServiceException;
    boolean delete(Offer offer) throws ServiceException;
    Offer getByRecordIdAndMasterId(Integer carRecordId, Integer masterId) throws ServiceException;
    void save(Offer offer) throws ServiceException;
    List<Offer> getOffersByCarRecords(List<CarRecord> carRecordList) throws ServiceException;
}
