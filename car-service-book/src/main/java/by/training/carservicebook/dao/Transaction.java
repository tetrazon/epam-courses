package by.training.carservicebook.dao;


import by.training.carservicebook.dao.exception.DaoException;

public interface Transaction {
	<T extends Dao<?>> T createDao(Class<T> key) throws DaoException;

	void commit() throws DaoException;

	void rollback() throws DaoException;
}
