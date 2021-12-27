package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.BaseDao;
import by.training.carservicebook.dao.UserDao;
import by.training.carservicebook.dao.exception.DaoException;
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
            "SELECT login, user.id, role, user.name, surname, email, mobile_phone, user.is_banned, d.name district" +
                    "  from user join district d on d.id = user.district_id " +
                    "WHERE user.id != ?";
    private static final String SQL_SELECT_USER_BY_ROLE =
            "SELECT user.id, user.name, surname, email, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id WHERE role=?";
    private static final String SQL_SELECT_USER_BY_ROLE_AND_DISTRICT =
            "SELECT user.id, user.name, surname, email, mobile_phone FROM user " +
                    "WHERE role=? AND district_id = (select id from district where district.name = ?)";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT user.id, user.name, surname, email, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id " +
                    "WHERE user.id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT user.id, user.name, surname, email, role, is_banned, d.name district, mobile_phone FROM user " +
                    "join district d on d.id = user.district_id " +
                    "WHERE user.login=? and user.password=?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT user.id, user.name FROM user WHERE user.login = ?";
    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM user WHERE id=?";
    private static final String SQL_CREATE_USER = "INSERT INTO user " +
            "(login, password, role, name, surname, email, district_id, mobile_phone, is_banned, is_archived)" +
            "VALUES (?,?,?,?,?,?,(select id from district where district.name = ?),?, 0, 0);";
    private static final String SQL_BAN_BY_ID = "UPDATE user SET is_banned = ? where id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE user SET login = ?, password = ?, " +
            "name = ?, surname = ?, email = ?, district_id = (select id from district where district.name = ?), " +
            "mobile_phone = ? WHERE user.id = ?";

    @Override
    public List<User> findAll(Integer except) throws DaoException {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            preparedStatement.setInt(1, except);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setDistrict(resultSet.getString("district"));
                user.setLogin(resultSet.getString("login"));
                user.setIsBanned(resultSet.getBoolean("is_banned"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return userList;
    }

    @Override
    public void banUser(Integer userId, boolean isBanned) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_BAN_BY_ID);
            preparedStatement.setBoolean(1, isBanned);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public boolean userExistsByLogin(String login) throws DaoException {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(login);
                log.debug("user: ", user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        log.debug(String.format("user: %s", user));
        return user != null;
    }

    @Override
    public List<User> findByRoleAndDistrict(Role role, String district) throws DaoException {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ROLE_AND_DISTRICT);

            preparedStatement.setInt(1, role.ordinal());
            preparedStatement.setString(2, district);
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
            close(preparedStatement);
        }
        return userList;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return findAll(0);
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
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDistrict());
            preparedStatement.setString(7, user.getMobilePhone());
            preparedStatement.setInt(8, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<User> findByRole(Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
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
            close(preparedStatement);
        }
        return userList;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setMobilePhone(resultSet.getString("mobile_phone"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                user.setIsBanned(resultSet.getBoolean("is_banned"));
                user.setDistrict(resultSet.getString("district"));
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
