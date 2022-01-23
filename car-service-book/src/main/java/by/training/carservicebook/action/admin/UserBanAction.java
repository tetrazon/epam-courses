package by.training.carservicebook.action.admin;

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
public class UserBanAction extends AdminAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/user/list.html");
		try{
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			boolean isBanned = Boolean.parseBoolean(request.getParameter("isBanned"));
			UserService service = factory.getService(UserService.class);
			service.banUser(userId, isBanned);
			log.debug(String.format("user with id %s banned: %s",userId, isBanned));
			if (isBanned){
				forward.getAttributes().put("message", "message.UserBanned");

			} else {
				forward.getAttributes().put("message", "message.UserUnbanned");
			}
			//forward.getAttributes().put("message", String.format("пользователь с id %s забанен: %s",userId, isBanned ? "да" : "нет"));
		} catch (NumberFormatException e) {
			log.error("cannot parse userId", e);
		}
		return forward;
	}
}
