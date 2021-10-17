package by.training.carservicebook.dao.mysql;


import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.Transaction;
import by.training.carservicebook.dao.TransactionFactory;
import by.training.carservicebook.dao.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class TransactionFactoryImpl implements TransactionFactory {
	private Connection connection;
	
	public TransactionFactoryImpl() throws DaoException {
		connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch(SQLException e) {
			log.error("It is impossible to turn off autocommiting for database connection", e);
			throw new DaoException(e);
		}
	}

	@Override
	public Transaction createTransaction() throws DaoException {
		return new TransactionImpl(connection);
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}
