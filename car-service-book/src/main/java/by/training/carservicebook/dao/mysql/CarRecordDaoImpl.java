package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.BaseDao;
import by.training.carservicebook.dao.CarRecordDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.User;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Log4j2
public class CarRecordDaoImpl extends BaseDao implements CarRecordDao {
    private static final String SQL_SELECT_ALL_RECORDS =
            "SELECT car_record.id, car_id, km_interval, months_interval, description, is_periodic, is_tender, date, c.name category" +
                    "  from car_record join category c on c.id = car_record.category_id";
    private static final String SQL_SELECT_ALL_BY_CAR_ID =
            "SELECT car_record.id, car_record.km_interval, car_record.months_interval, car_record.description, car_record.is_periodic, is_tender, date, c.name category" +
                    "  from car_record join category c on c.id = car_record.category_id" +
                    " where car_id = ?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT car_id, km_interval, months_interval, car_record.description, is_periodic, is_tender, date, c.name category" +
                    "  from car_record join category c on c.id = car_record.category_id" +
                    " where car_record.id = ?";
    private static final String SQL_SELECT_HISTORY_BY_CAR_ID =
            "SELECT date_performed, work_price, master_id, description from car_record_history where car_id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE car_record SET km_interval = ?, months_interval = ?, " +
            "description = ?, is_periodic = ?, is_tender = ?, date = ?, " +
            "category_id = (select id from category where name = ?) WHERE car_record.id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE from car_record WHERE car_record.id = ?";
    private static final String SQL_CREATE =
            "INSERT INTO car_service_db.car_record (car_id, km_interval, months_interval, description, " +
                    "is_periodic, is_tender, date, category_id)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, (select id from category where name = ?))";
    private static final String SQL_CREATE_HISTORY =
            "INSERT INTO car_service_db.car_record_history (car_id, date_performed, work_price, master_id, description) " +
                     "VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<CarRecord> findCarRecordHistoryByCarId(Integer carId) throws DaoException {
        List<CarRecord> recordList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_HISTORY_BY_CAR_ID);
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarRecord carRecord = new CarRecord();
                carRecord.setRecordDate(resultSet.getDate("date_performed"));
                carRecord.setDescription(resultSet.getString("description"));
                carRecord.setWorkPrice(resultSet.getDouble("work_price"));
                carRecord.setMaster(new User(resultSet.getInt("master_id")));
                recordList.add(carRecord);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return recordList;
    }

    @Override
    public List<CarRecord> findCarRecordByCarId(Integer carId) throws DaoException {
        List<CarRecord> recordList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_CAR_ID);
                preparedStatement.setInt(1, carId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CarRecord carRecord = new CarRecord();
                    carRecord.setId(resultSet.getInt("id"));
                    carRecord.setKmInterval(resultSet.getInt("km_interval"));
                    carRecord.setIsPeriodic(resultSet.getBoolean("is_periodic"));
                    carRecord.setIsTender(resultSet.getBoolean("is_tender"));
                    carRecord.setRecordDate(resultSet.getDate("date"));
                    carRecord.setCategory(resultSet.getString("category"));
                    carRecord.setDescription(resultSet.getString("description"));
                    carRecord.setMonthsInterval(resultSet.getInt("months_interval"));
                    recordList.add(carRecord);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                close(preparedStatement);
            }
            return recordList;
        }

    @Override
    public List<CarRecord> findAll() throws DaoException {
        List<CarRecord> recordList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while(resultSet.next()){
                CarRecord carRecord = new CarRecord();
                final Car car = new Car();
                carRecord.setCar(car);
                car.setId(resultSet.getInt("car_id"));
                carRecord.setId(resultSet.getInt("id"));
                carRecord.setCar(car);
                carRecord.setKmInterval(resultSet.getInt("km_interval"));
                carRecord.setIsPeriodic(resultSet.getBoolean("is_periodic"));
                carRecord.setIsTender(resultSet.getBoolean("is_tender"));
                carRecord.setRecordDate(resultSet.getDate("date"));
                carRecord.setCategory(resultSet.getString("category"));
                carRecord.setDescription(resultSet.getString("description"));
                carRecord.setMonthsInterval(resultSet.getInt("months_interval"));
                recordList.add(carRecord);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return recordList;
    }

    @Override
    public CarRecord findById(Integer id) throws DaoException {
        CarRecord carRecord = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                carRecord = new CarRecord();
                final Car car = new Car();
                carRecord.setCar(car);
                car.setId(resultSet.getInt("car_id"));
                carRecord.setId(id);
                carRecord.setCar(car);
                carRecord.setKmInterval(resultSet.getInt("km_interval"));
                carRecord.setIsPeriodic(resultSet.getBoolean("is_periodic"));
                carRecord.setIsTender(resultSet.getBoolean("is_tender"));
                carRecord.setRecordDate(resultSet.getDate("date"));
                carRecord.setCategory(resultSet.getString("category"));
                carRecord.setDescription(resultSet.getString("description"));
                carRecord.setMonthsInterval(resultSet.getInt("months_interval"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return carRecord;
    }

    @Override
    public boolean delete(CarRecord carRecord) throws DaoException {
       return delete(carRecord.getId());
    }

    @Override
    public boolean delete(Integer carRecordId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, carRecordId);
            final int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Integer create(CarRecord carRecord) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, carRecord.getCar().getId());
            statement.setInt(2, carRecord.getKmInterval());
            statement.setInt(3, carRecord.getMonthsInterval());
            statement.setString(4, carRecord.getDescription());
            statement.setBoolean(5, carRecord.getIsPeriodic());
            statement.setBoolean(6, carRecord.getIsTender());
            statement.setDate(7, carRecord.getRecordDate());
            statement.setString(8, carRecord.getCategory());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincrement index after trying to add record into table car_record");
                throw new DaoException();
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public Integer addToHistory(CarRecord carRecord) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_HISTORY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, carRecord.getCar().getId());
            statement.setDate(2, carRecord.getRecordDate());
            statement.setDouble(3, carRecord.getWorkPrice());
            statement.setInt(4, carRecord.getMaster().getId());
            statement.setString(5, carRecord.getDescription());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincrement index after trying to add record into table car_record_history");
                throw new DaoException();
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public void update(CarRecord carRecord) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setInt(1, carRecord.getKmInterval());
            preparedStatement.setInt(2, carRecord.getMonthsInterval());
            preparedStatement.setString(3, carRecord.getDescription());
            preparedStatement.setBoolean(4, carRecord.getIsPeriodic());
            preparedStatement.setBoolean(5, carRecord.getIsTender());
            preparedStatement.setDate(6, carRecord.getRecordDate());
            preparedStatement.setString(7, carRecord.getCategory());
            preparedStatement.setInt(8, carRecord.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }
}
