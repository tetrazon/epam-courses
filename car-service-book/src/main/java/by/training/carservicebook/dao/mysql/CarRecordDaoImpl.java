package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.CarRecordDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Log4j2
public class CarRecordDaoImpl implements CarRecordDao {
    private Connection connection;
    private static final String SQL_SELECT_ALL_RECORDS =
            "SELECT car_record.id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, c.name category" +
                    "  from car_record join category c on c.id = car_record.category_id";
    private static final String SQL_SELECT_ALL_BY_CAR_ID =
            "SELECT car_record.id, km_interval, months_interval, description, is_periodic, is_tender, date, c.name category" +
                    "  from car_record join category c on c.id = car_record.category_id" +
                    " where car_id = ?";
    @Override
    public List<CarRecord> findCarRecordIsOnTender() {
        List<CarRecord> recordList = new ArrayList<>();
        /*Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setDistrict(resultSet.getString("district"));
                recordList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }*/
        return recordList;
    }

    @Override
    public List<CarRecord> findCarRecordHistoryByCarId(Integer carId) {
        return null;
    }

    @Override
    public List<CarRecord> findCarRecordByCarId(Integer carId) throws DaoException {
        List<CarRecord> recordList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
            try {
                //connection = ConnectionCreator.createConnection();
                preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_CAR_ID);
                preparedStatement.setInt(1, carId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CarRecord carRecord = new CarRecord();
                    carRecord.setId(resultSet.getInt("id"));
                    carRecord.setKmInterval(resultSet.getInt("km_interval"));
                    carRecord.setPeriodicOperation(resultSet.getBoolean("is_periodic"));
                    carRecord.setOnTender(resultSet.getBoolean("is_tender"));
                    carRecord.setRecordDate(resultSet.getDate("date"));
                    carRecord.setCategory(resultSet.getString("category"));
                    recordList.add(carRecord);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                close(preparedStatement);
                close(connection);
            }
            return recordList;
        }

    @Override
    public void updateCarRecordDate(Date date) {

    }

    @Override
    public void updateCarRecordIsOnTender(boolean isOnTender) {

    }

    @Override
    public void addCarRecordHistory(CarRecord carRecordHistory) {

    }

    @Override
    public List<CarRecord> findAll() throws DaoException {
        List<CarRecord> recordList = new ArrayList<>();
        Statement statement = null;
        try {
            //connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            //car_record.id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, c.name category
            while(resultSet.next()){
                CarRecord carRecord = new CarRecord();
                final Car car = new Car();
                carRecord.setCar(car);
                car.setId(resultSet.getInt("car_id"));
                carRecord.setId(resultSet.getInt("id"));
                carRecord.setCar(car);
                carRecord.setKmInterval(resultSet.getInt("km_interval"));
                carRecord.setPeriodicOperation(resultSet.getBoolean("is_periodic"));
                carRecord.setOnTender(resultSet.getBoolean("is_tender"));
                carRecord.setRecordDate(resultSet.getDate("date"));
                carRecord.setCategory(resultSet.getString("category"));
                recordList.add(carRecord);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return recordList;
    }

    @Override
    public CarRecord findById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(CarRecord carRecord) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public Integer create(CarRecord carRecord) throws DaoException {
        return null;
    }

    @Override
    public CarRecord update(CarRecord carRecord) throws DaoException {
        return null;
    }
}
