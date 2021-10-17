package by.training.carservicebook.service;

import by.training.carservicebook.dao.exception.DaoException;
import lombok.extern.log4j.Log4j2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Log4j2
public class ServiceInvocationHandlerImpl implements InvocationHandler {

	private ServiceImpl service;

	public ServiceInvocationHandlerImpl(ServiceImpl service) {
		this.service = service;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
		try {
			Object result = method.invoke(service, arguments);
			service.transaction.commit();
			return result;
		} catch(DaoException e) {
			rollback(method);
			throw e;
		} catch(InvocationTargetException e) {
			rollback(method);
			throw e.getCause();
		}
	}

	private void rollback(Method method) {
		try {
			service.transaction.rollback();
		} catch(DaoException e) {
			log.warn("It is impossible to rollback transaction", e);
		}
	}
}
