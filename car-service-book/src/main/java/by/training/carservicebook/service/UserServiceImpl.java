package by.training.carservicebook.service;

import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.UserDao;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UserServiceImpl extends ServiceImpl implements UserService {
	@Override
	public List<User> findAll() throws ServiceException {
		UserDao dao = null;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findAll();
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public User findById(Integer id) throws ServiceException {
		UserDao dao = null;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findById(id);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public User findByLoginAndPassword(String login, String password) throws ServiceException {
		UserDao dao = null;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.findByLoginAndPassword(login, password);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public void save(User user) throws ServiceException {
		UserDao dao = null;
		try {
			dao = transaction.createDao(UserDao.class);
			if(user.getId() != null) {
				User oldUser = dao.findById(user.getId());
				user.setPassword(oldUser.getPassword());
				dao.update(user);
			} else {
				user.setId(dao.create(user));
			}
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean delete(Integer id) throws ServiceException {
		UserDao dao = null;
		try {
			dao = transaction.createDao(UserDao.class);
			return dao.delete(id);
		} catch (DaoException e) {
			log.error("Dao exception");
			throw new ServiceException(e);
		}

	}

}
