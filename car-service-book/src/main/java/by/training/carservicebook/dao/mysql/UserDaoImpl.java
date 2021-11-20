package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.BaseDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.UserDao;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Log4j2
public class UserDaoImpl extends BaseDao implements UserDao {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT user.id, role, user.name, surname, email, mobile_phone, d.name district" +
                    "  from user join district d on d.id = user.district_id";
    private static final String SQL_SELECT_USER_BY_ROLE =
            "SELECT user.id, user.name, surname, email, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id WHERE role=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT user.id, user.name, surname, email, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id " +
                    "WHERE user.id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT user.id, user.name, surname, email, role, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id " +
                    "WHERE user.login=? and user.password=?";
    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM user WHERE id=?";
    private static final String SQL_CREATE_USER = "INSERT INTO user " +
            "(login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)" +
            "VALUES (?,?,?,?,?,?,(select id from district where district.name = ?),?, 0, 0);";
    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        try {
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
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userList;
    }

    @Override
    public User findById(Integer id) throws DaoException {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) throws DaoException{
        PreparedStatement preparedStatement = null;
        int isDeleted = 0;
        try {
            //connection = ConnectionCreator.createConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return isDeleted == 1;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return delete(user.getId());
    }

    @Override
    public Integer create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //(login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)
            statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getIdentity());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getDistrict());
            statement.setString(8, user.getMobilePhone());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincremented index after trying to add record into table `users`");
                throw new DaoException();
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public void update(User user) throws DaoException {
    }

    @Override
    public List<User> findUserByRole(Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ROLE);

            preparedStatement.setInt(1, role.ordinal());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userList;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                user.setLogin(login);
                log.debug("user: ", user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return user;
    }
}
