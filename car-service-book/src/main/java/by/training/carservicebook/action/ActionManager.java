package by.training.carservicebook.action;

import by.training.carservicebook.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionManager {
	Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void close();
}
