package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CarEditAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		log.debug("car edit action");
		try{
			Integer carId = (Integer)request.getAttribute("carId");
			if(carId == null) {
				carId = Integer.parseInt(request.getParameter("carId"));
			}
			CarService service = factory.getService(CarService.class);
			Car car = service.getCarById(carId);
			log.debug(String.format("car %s", car));
			if (car != null){
				request.setAttribute("car", car);
			}

		} catch (NumberFormatException e) {
		}
		return null;
	}
}
