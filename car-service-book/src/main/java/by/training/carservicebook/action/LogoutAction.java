package by.training.carservicebook.action;

import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Log4j2
public class LogoutAction extends AuthorizedUserAction {

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		User user = (User) request.getSession(false).getAttribute("authorizedUser");
		log.info(String.format("user \"%s\" is logged out", user.getLogin()));
		request.getSession(false).invalidate();
		return new Action.Forward("/login.html");
	}
}
