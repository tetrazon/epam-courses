package by.training.carservicebook.service;

import by.training.carservicebook.dao.Transaction;

abstract public class ServiceImpl implements Service {
	protected Transaction transaction = null;

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
