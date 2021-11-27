package by.training.carservicebook.service;

import by.training.carservicebook.dao.CarDao;
import by.training.carservicebook.dao.CarRecordDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CarRecordServiceImpl extends ServiceImpl implements CarRecordService{

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
    public boolean deleteById(CarRecord carRecord) throws ServiceException {
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
}
