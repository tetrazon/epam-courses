package by.training.carservicebook;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.connection.ConnectionCreator;
import by.training.carservicebook.dao.mysql.CarRecordDaoImpl;
import by.training.carservicebook.dao.mysql.UserDaoImpl;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;

import java.sql.*;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        /*try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        try(Connection connection = ConnectionCreator.createConnection()){
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            User user = new User();
            //(login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
            user.setLogin("test1");
            user.setPassword("test1");
            user.setRole(Role.CLIENT);
            user.setName("test1");
            user.setSurname("test1");
            user.setEmail("test1@email.w");
            user.setDistrict("Брест");
            user.setMobilePhone("+375291234567");
            user.setId(userDao.create(user));
            List<User> userList = userDao.findAll();
            System.out.println(userList);
            userDao.delete(user);
            System.out.println("user deleted\n");
            userList = userDao.findAll();
            System.out.println(userList);
            CarRecordDaoImpl carRecordDao = new CarRecordDaoImpl();
            carRecordDao.setConnection(connection);
            final List<CarRecord> carRecords = carRecordDao.findAll();
            System.out.println(carRecords);
            /*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM car");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    }
