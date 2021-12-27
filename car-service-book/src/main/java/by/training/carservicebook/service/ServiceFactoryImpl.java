package by.training.carservicebook.service;



import by.training.carservicebook.dao.exception.DaoException;
import by.training.carservicebook.dao.Transaction;
import by.training.carservicebook.dao.TransactionFactory;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class ServiceFactoryImpl implements ServiceFactory {

	private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

	static {
		SERVICES.put(UserService.class, UserServiceImpl.class);
		SERVICES.put(CarService.class, CarServiceImpl.class);
		SERVICES.put(CarRecordService.class, CarRecordServiceImpl.class);
		SERVICES.put(OfferService.class, OfferServiceImpl.class);
	}

	private TransactionFactory factory;

	public ServiceFactoryImpl(TransactionFactory factory) throws ServiceException {
		this.factory = factory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Service> T getService(Class<T> key) throws ServiceException {
		Class<? extends ServiceImpl> value = SERVICES.get(key);
		if(value != null) {
			try {
				ClassLoader classLoader = value.getClassLoader();
				Class<?>[] interfaces = {key};
				Transaction transaction = factory.createTransaction();
				ServiceImpl service = value.newInstance();
				service.setTransaction(transaction);
				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (T)Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch(DaoException e) {
				throw new ServiceException(e);
			} catch(InstantiationException | IllegalAccessException e) {
				log.error("It is impossible to instance service class", e);
				throw new ServiceException(e);
			}
		}
		return null;
	}

	@Override
	public void close() {
		factory.close();
	}
}
