package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.BaseDao;
import by.training.carservicebook.dao.CarDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.User;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CarDaoImpl extends BaseDao implements CarDao {
    private static final String SQL_SELECT_ALL_BY_USER_ID =
            "SELECT id, model, mileage, year from car where user_id = ?";
    private static final String SQL_DELETE_ALL_BY_USER_ID =
            "delete from car where user_id = ?";
    private static final String SQL_DELETE_BY_ID =
            "delete from car where id = ?";
    private static final String SQL_SELECT_ALL =
            "SELECT id, model, mileage, year, user_id from car";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id, model, mileage, year, user_id from car where id = ?";
    private static final String SQL_CREATE =
            "INSERT INTO car (model, mileage, year, user_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE car SET model = ?, mileage = ?, year = ? WHERE id = ?";


    @Override
    public List<Car> getCarByUserId(Integer userId) throws DaoException {
        List<Car> carList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setYear(resultSet.getInt("year"));
                car.setModel(resultSet.getString("model"));
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return carList;
    }

    @Override
    public int deleteCarByUserId(Integer userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setYear(resultSet.getInt("year"));
                car.setModel(resultSet.getString("model"));
                car.setUser(new User(resultSet.getInt("userId")));
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return carList;
    }

    @Override
    public Car findById(Integer carId) throws DaoException {
        Car car = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setYear(resultSet.getInt("year"));
                car.setModel(resultSet.getString("model"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return car;
    }

    @Override
    public boolean delete(Car car) throws DaoException {

        return delete(car.getId());
    }

    @Override
    public boolean delete(Integer carId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, carId);
            final int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Integer create(Car car) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, car.getModel());
            statement.setDouble(2, car.getMileage());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getUser().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincrement index after trying to add record into table car");
                throw new DaoException();
            }
        } catch (SQLException e){
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public void update(Car car) throws DaoException {
        String sql = "UPDATE car SET model = ?, mileage = ?, year = ? WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, car.getModel());
            statement.setDouble(2, car.getMileage());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }
}
