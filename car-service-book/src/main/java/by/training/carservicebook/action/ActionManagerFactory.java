package by.training.carservicebook.action;

import by.training.carservicebook.service.ServiceFactory;

public class ActionManagerFactory {
	public static ActionManager getManager(ServiceFactory factory) {
		return new ActionManagerImpl(factory);
	}
}
