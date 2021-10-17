package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.CarDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.connection.ConnectionCreator;
import by.training.carservicebook.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private Connection connection;
    private static final String SQL_SELECT_ALL_BY_USER_ID =
            "SELECT id, model, mileage, year from car where user_id = ?";
    @Override
    public List<Car> getCarByUserId(Integer userId) throws DaoException {
        List<Car> carList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionCreator.createConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setYear(resultSet.getInt("year"));
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return carList;
    }

    @Override
    public int deleteCarByUserId(Integer id) {
        return 0;
    }

    @Override
    public List<Car> findAll() throws DaoException {
        return null;
    }

    @Override
    public Car findById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Car car) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public Integer create(Car car) throws DaoException {
        return null;
    }

    @Override
    public Car update(Car car) throws DaoException {
        return null;
    }
}
