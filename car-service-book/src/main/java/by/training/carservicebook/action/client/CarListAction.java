package by.training.carservicebook.action.client;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
public class CarListAction extends ClientAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
		if(user != null) {
			CarService carService = factory.getService(CarService.class);
			final List<Car> carList = carService.getCarByUserId(user.getId());
			log.debug(String.format("cars: %s", carList));
			request.getSession().setAttribute("carList", carList);
		}
		return null;
	}
}
