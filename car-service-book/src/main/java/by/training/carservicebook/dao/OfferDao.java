package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.exception.ServiceException;

import java.util.List;

public interface OfferDao extends Dao<Offer>{
    //List<Offer> getAll(Integer id) throws DaoException;
    void deleteByCarRecordId(Integer carRecordId) throws DaoException;
    Offer getByRecordIdAndMasterId(Integer carRecordId, Integer masterId) throws DaoException;
    //Integer save(Offer offer) throws DaoException;
}
