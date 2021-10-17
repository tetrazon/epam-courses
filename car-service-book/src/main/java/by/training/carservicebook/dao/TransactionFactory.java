package by.training.carservicebook.dao;

import by.training.carservicebook.dao.exception.DaoException;

public interface TransactionFactory {
	Transaction createTransaction() throws DaoException;

	void close();
}
