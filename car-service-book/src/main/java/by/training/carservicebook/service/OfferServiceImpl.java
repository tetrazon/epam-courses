package by.training.carservicebook.service;

import by.training.carservicebook.dao.CarDao;
import by.training.carservicebook.dao.OfferDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
public class OfferServiceImpl extends ServiceImpl implements OfferService {
    @Override
    public List<Offer> getAll() throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            return dao.findAll();
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    /*@Override
    public List<Offer> getByMasterId(Integer masterId) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            List<Offer> offerList = dao.findAll();
            return offerList.stream()
                    .filter(offer -> Objects.equals(offer.getMaster().getId(), masterId))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }*/

    @Override
    public List<Offer> getSelectedByMasterId(Integer masterId) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            List<Offer> offerList = dao.findAll();
            log.debug(String.format("offerList: %s", offerList));
            return offerList.stream()
                    .filter(offer -> Objects.equals(offer.getMaster().getId(), masterId) && offer.getIsSelected())
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByCarRecordId(Integer carRecordId) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            dao.deleteByCarRecordId(carRecordId);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Offer offer) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            return dao.delete(offer);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public Offer getByRecordIdAndMasterId(Integer carRecordId, Integer masterId) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            return dao.getByRecordIdAndMasterId(carRecordId, masterId);
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Offer offer) throws ServiceException {
        OfferDao dao;
        try {
            dao = transaction.createDao(OfferDao.class);
            log.debug(String.format("offer: %s", offer));
            Offer foundOffer =  dao.getByRecordIdAndMasterId(offer.getCarRecord().getId(), offer.getMaster().getId());
            if (foundOffer != null){
                dao.update(offer);
            } else {
                dao.create(offer);
            }
        } catch (DaoException e) {
            log.error("Dao exception");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Offer> getOffersByCarRecords(List<CarRecord> carRecordList) throws ServiceException {
        List<Offer> offerList = getAll();
        List<Integer> carRecordIdList = carRecordList.parallelStream()
                        .map(CarRecord::getId)
                        .collect(Collectors.toList());
        offerList = offerList.parallelStream()
                .filter(offer -> carRecordIdList.contains(offer.getCarRecord().getId()))
                .map(offer -> new Offer(carRecordList.parallelStream()
                                .filter(carRecord -> offer.getCarRecord().getId().equals(carRecord.getId()))
                                .findFirst()
                                .orElse(offer.getCarRecord()), offer.getMaster(), offer.getPrice(), offer.getIsSelected()
                                ))
                .collect(Collectors.toList());

        return offerList;
    }
}
