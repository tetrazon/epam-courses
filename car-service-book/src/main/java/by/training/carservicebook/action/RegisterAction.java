package by.training.carservicebook.action;

import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class RegisterAction extends Action {

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		log.debug("register user action");
		return null;
	}
}
