package by.training.carservicebook.dao.mysql;

import by.training.carservicebook.dao.*;
import by.training.carservicebook.dao.exception.DaoException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class TransactionImpl implements Transaction {

	private static Map<Class<? extends Dao<?>>, Class<? extends BaseDao>> classes = new ConcurrentHashMap<>();
	static {
		classes.put(UserDao.class, UserDaoImpl.class);
		classes.put(CarDao.class, CarDaoImpl.class);
		classes.put(CarRecordDao.class, CarRecordDaoImpl.class);
		classes.put(OfferDao.class, OfferDaoImpl.class);
	}

	private Connection connection;

	public TransactionImpl(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException {
		Class<? extends BaseDao> value = classes.get(key);
		if(value != null) {
			try {
				BaseDao dao = value.newInstance();
				dao.setConnection(connection);
				return (Type)dao;
			} catch(InstantiationException | IllegalAccessException e) {
				log.error("It is impossible to create data access object", e);
				throw new DaoException(e);
			}
		}
		return null;
	}

	@Override
	public void commit() throws DaoException {
		try {
			connection.commit();
		} catch(SQLException e) {
			log.error("It is impossible to commit transaction", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void rollback() throws DaoException {
		try {
			connection.rollback();
		} catch(SQLException e) {
			log.error("It is impossible to rollback transaction", e);
			throw new DaoException(e);
		}
	}
}
