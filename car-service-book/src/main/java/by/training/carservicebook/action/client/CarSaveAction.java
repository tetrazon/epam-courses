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
import java.util.List;

@Log4j2
public class CarSaveAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car/list.html");
		Validator<Car> validator = ValidatorFactory.createValidator(Car.class);
		final User user = getAuthorizedUser();
		try {
			final Car car = validator.validate(request);
			log.debug("save car action");
			if(user != null) {
				car.setUser(user);
			} else throw new IncorrectFormDataException("user", null);
			CarService service = factory.getService(CarService.class);
			final Integer carId = service.save(car);
			log.debug(String.format("user %s saved car with id: %s", user.getLogin() ,carId));
			forward.getAttributes().put("carId", carId);
			forward.getAttributes().put("message", "Данные авто успешно сохранены");

		} catch (IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			log.warn(String.format("Incorrect data was found when user \"%s\" tried to save car", user.getLogin()), e);
		}
		return forward;
	}
}
