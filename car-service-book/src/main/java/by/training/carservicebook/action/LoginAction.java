package by.training.carservicebook.action;

import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class LoginAction extends Action {

	private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

	static {
		menu.put(Role.CLIENT, new ArrayList<>(Arrays.asList(
				new MenuItem("/car/list.html", "мои авто")
		)));
		menu.put(Role.ADMIN, new ArrayList<>(Arrays.asList(
				new MenuItem("/user/list.html", "администрирование")
		)));
		menu.put(Role.MASTER, new ArrayList<>(Arrays.asList(
				new MenuItem("/car_record/tender_list.html", "тендер"),
				new MenuItem("/car_record/approved.html", "одобреные работы")
		)));
	}

	@Override
	public Set<Role> getAllowRoles() {
		return null;
	}

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login != null && password != null) {
			UserService service = factory.getService(UserService.class);
			User user = service.findByLoginAndPassword(login, password);
			if(user != null) {
				if(user.getIsBanned()){
					request.setAttribute("message", "Пользователь заблокирован. Пожалуйста, обратитесь к администратору");
					log.info(String.format("banned user \"%s\" tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
					return null;
				}
				HttpSession session = request.getSession();
				session.setAttribute("authorizedUser", user);
				session.setAttribute("menu", menu.get(user.getRole()));
				log.debug(String.format("user: %s", user));
				log.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
				return new Forward("/index.html");
			} else {
				request.setAttribute("message", "Имя пользователя или пароль не опознаны");
				log.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
			}
		}
		return null;
	}
}
