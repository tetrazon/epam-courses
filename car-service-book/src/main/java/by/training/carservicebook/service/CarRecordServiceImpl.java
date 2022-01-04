package by.training.carservicebook.service;

import by.training.carservicebook.dao.CarRecordDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class CarRecordServiceImpl extends ServiceImpl implements CarRecordService{


    private Integer addToHistory(CarRecord carRecord) throws ServiceException {
        CarRecordDao  dao;
        Integer carRecordId;
        try {
            dao = transaction.createDao(CarRecordDao.class);
           carRecordId = dao.addToHistory(carRecord);
            return carRecordId;
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }
    @Override
    public List<CarRecord> findCarRecordByCarId(Integer carId) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            return dao.findCarRecordByCarId(carId);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(CarRecord carRecord) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            return dao.delete(carRecord.getId());
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer save(CarRecord carRecord) throws ServiceException {
        CarRecordDao  dao;
        Integer carRecordId;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            if(carRecord.getId() == null){
                carRecordId = dao.create(carRecord);
            } else {
                dao.update(carRecord);
                carRecordId = carRecord.getId();
            }
            return carRecordId;
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public CarRecord findById(Integer id) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            return dao.findById(id);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CarRecord> findByCarIdAndTender(Integer carId, boolean isTender) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            List<CarRecord> carRecordList = dao.findCarRecordByCarId(carId);
            return carRecordList.stream().filter(carRecord -> carRecord.getIsTender()==isTender).collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CarRecord> findByOffers(List<Offer> offerList) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            List<CarRecord> carRecordList = new ArrayList<>();
            for (Offer offer : offerList) {
                CarRecord carRecord = dao.findById(offer.getMaster().getId());
                if (carRecord != null){
                    carRecordList.add(carRecord);
                }
            }
            return carRecordList;
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public void markDone(CarRecord carRecord) throws ServiceException {
        CarRecord carRecordFromDao = findById(carRecord.getId());
        if (carRecordFromDao.getIsPeriodic()){
            carRecordFromDao.setRecordDate(new Date(System.currentTimeMillis()));
            save(carRecordFromDao);
        } else {
            delete(carRecordFromDao);
        }
        carRecord.setCar(carRecordFromDao.getCar());
        carRecord.setRecordDate(new Date(System.currentTimeMillis()));
        carRecord.setDescription(carRecordFromDao.getDescription());
        addToHistory(carRecord);
    }

    @Override
    public List<CarRecord> findCarRecordHistoryByCarId(Integer carId) throws ServiceException {
        CarRecordDao dao;
        try {
            dao = transaction.createDao(CarRecordDao.class);
            return dao.findCarRecordHistoryByCarId(carId);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

}
