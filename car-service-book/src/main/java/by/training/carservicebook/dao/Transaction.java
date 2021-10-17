package by.training.carservicebook.dao;


import by.training.carservicebook.dao.exception.DaoException;

public interface Transaction {
	<Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException;

	void commit() throws DaoException;

	void rollback() throws DaoException;
}
