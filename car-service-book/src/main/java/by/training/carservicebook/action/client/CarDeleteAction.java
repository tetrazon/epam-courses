package by.training.carservicebook.action.client;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.Car;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Log4j2
public class CarDeleteAction extends ClientAction {

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car/list.html");
		try {
			CarService service = factory.getService(CarService.class);
			Integer carId = Integer.parseInt(request.getParameter("carId"));
			Car car = service.getCarById(carId);
			if(car != null) {
				service.deleteCar(car);
				forward.getAttributes().put("message", "Авто успешно удалено");
				log.info(String.format("User \"%s\" deleted car with id %d", getAuthorizedUser().getLogin(), carId));
			}
		} catch(NumberFormatException e) {
			log.warn(String.format("Incorrect data was found when user \"%s\" tried to delete car", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
