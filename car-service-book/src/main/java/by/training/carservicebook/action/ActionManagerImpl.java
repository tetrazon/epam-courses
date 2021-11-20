package by.training.carservicebook.action;

import by.training.carservicebook.service.ServiceFactory;
import by.training.carservicebook.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
	private ServiceFactory factory;

	public ActionManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	@Override
	public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		action.setFactory(factory);
		return action.exec(request, response);
	}

	@Override
	public void close() {
		factory.close();
	}
}
