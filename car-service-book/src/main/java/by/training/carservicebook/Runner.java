package by.training.carservicebook;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.connection.ConnectionCreator;
import by.training.carservicebook.dao.mysql.CarRecordDaoImpl;
import by.training.carservicebook.dao.mysql.TransactionFactoryImpl;
import by.training.carservicebook.dao.mysql.UserDaoImpl;
import by.training.carservicebook.dao.pool.ConnectionPool;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.ServiceFactoryImpl;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.UserServiceImpl;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

@Log4j2
public class Runner {
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(ConnectionCreator.class.getResourceAsStream("/database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
            ConnectionPool.getInstance().init(driverName, (String) properties.get("db.url"), (String) properties.get("user"),
                    (String) properties.get("password"), DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
            try {
                final ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl(new TransactionFactoryImpl());
                final UserService service = serviceFactory.getService(UserService.class);
                final User user = service.findByLoginAndPassword("admin", "admin");
                System.out.println(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } catch(DaoException | IOException e) {
            log.error("It is impossible to initialize application", e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }

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
       /* try(Connection connection = ConnectionCreator.createConnection()){
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connection);
            User user = new User();
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
            *//*Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM car");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }*//*
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }*/
    }
    }
