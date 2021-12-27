package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.BaseDao;
import by.training.carservicebook.dao.OfferDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.*;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OfferDaoImpl extends BaseDao implements OfferDao {

    private static final String SQL_SELECT_ALL =
            "SELECT car_record_id, master_id, price, is_selected from service_tender";
    private static final String SQL_SELECT_BY_RECORD_ID_AND_MASTER_ID =
            "SELECT price, is_selected from service_tender WHERE car_record_id = ? AND master_id = ?";
    private static final String SQL_CREATE =
            "INSERT INTO service_tender (car_record_id, master_id, price, is_selected) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE service_tender SET price = ?, is_selected = ? WHERE car_record_id = ? AND master_id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM service_tender WHERE car_record_id = ? AND master_id = ?";
    private static final String SQL_DELETE_BY_RECORD_ID =
            "DELETE FROM service_tender WHERE car_record_id = ? ";

    @Override
    public List<Offer> findAll() throws DaoException {

        List<Offer> offerList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Offer offer = new Offer();
                offer.setCarRecord(new CarRecord(resultSet.getInt("car_record_id")));
                User master = new User(resultSet.getInt("master_id"));
                master.setRole(Role.MASTER);
                offer.setMaster(master);
                offer.setPrice(resultSet.getDouble("price"));
                offer.setIsSelected(resultSet.getBoolean("is_selected"));
                offerList.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return offerList;
    }

    @Override
    public Offer findById(Integer id) throws DaoException {
        log.error("not implemented");
        throw new DaoException("call getByRecordIdAndMasterId instead");
    }

    @Override
    public boolean delete(Offer offer) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, offer.getCarRecord().getId());
            preparedStatement.setInt(2, offer.getMaster().getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        log.error("not implemented");
        throw new DaoException("call  delete(Offer offer) instead");
    }

    @Override
    public Integer create(Offer offer) throws DaoException {
        PreparedStatement statement = null;
        try {
            //"INSERT INTO service_tender (car_record_id, master_id, price, is_selected) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, offer.getCarRecord().getId());
            statement.setInt(2, offer.getMaster().getId());
            statement.setDouble(3, offer.getPrice());
            statement.setBoolean(4, offer.getIsSelected());
            statement.executeUpdate();
            return 0;//no id
        } catch (SQLException e){
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public void update(Offer offer) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setDouble(1, offer.getPrice());
            statement.setBoolean(2, offer.getIsSelected());
            statement.setInt(3, offer.getCarRecord().getId());
            statement.setInt(4, offer.getMaster().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public void deleteByCarRecordId(Integer carRecordId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_RECORD_ID);
            preparedStatement.setInt(1, carRecordId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Offer getByRecordIdAndMasterId(Integer carRecordId, Integer masterId) throws DaoException {
        Offer offer = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_RECORD_ID_AND_MASTER_ID);
            statement.setInt(1, carRecordId);
            statement.setInt(2, masterId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                offer = new Offer();
                offer.setCarRecord(new CarRecord(carRecordId));
                User master = new User(masterId);
                master.setRole(Role.MASTER);
                offer.setMaster(master);
                offer.setPrice(resultSet.getDouble("price"));
                offer.setIsSelected(resultSet.getBoolean("is_selected"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return offer;
    }
}
