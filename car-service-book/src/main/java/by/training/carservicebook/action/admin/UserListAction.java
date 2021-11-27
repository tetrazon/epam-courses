package by.training.carservicebook.action.admin;

import by.training.carservicebook.action.client.ClientAction;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
public class UserListAction extends AdminAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
		if(user != null) {
			UserService service = factory.getService(UserService.class);
			final List<User> userList = service.findAll(user.getId());
			request.getSession().setAttribute("userList", userList);
		}
		return null;
	}
}
