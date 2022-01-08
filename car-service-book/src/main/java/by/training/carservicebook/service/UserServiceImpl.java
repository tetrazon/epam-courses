package by.training.carservicebook.service;

import by.training.carservicebook.dao.UserDao;
import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.hash.HashGenerator;
import by.training.carservicebook.hash.HashGeneratorFactory;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserServiceImpl extends ServiceImpl implements UserService {

	private final HashGenerator hashGenerator = HashGeneratorFactory.getHashGenerator("Bcrypt");

	@Override
	public List<User> findAll(Integer except) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findAll(except);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public User findById(Integer id) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findById(id);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean existByLogin(String login) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.userExistsByLogin(login);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}
	}

	@Override
	public User findByLogin(String login) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findByLogin(login);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}
	}

	@Override
	public User findByLoginAndPassword(String login, String password) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			User user = dao.findByLogin(login);
			boolean isSamePassword = hashGenerator.check(password, user.getPassword());
			return isSamePassword ? user : null;
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public void save(User user) throws ServiceException {
		UserDao dao;
		user.setPassword(hashGenerator.hash(user.getPassword()));
		try {
			dao = transaction.createDao(UserDao.class);
			if(user.getId() != null) {
				dao.update(user);
			} else {
				dao.create(user);
			}
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean delete(Integer id) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.delete(id);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public void banUser(Integer userId, boolean isBanned) throws ServiceException{
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			dao.banUser(userId, isBanned);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findByRoleAndDistrict(Role role, String district) throws ServiceException {
		UserDao dao;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findByRoleAndDistrict(role, district);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}
	}

}
