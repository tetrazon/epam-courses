package by.training.carservicebook.validator;

import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class UserValidator implements Validator<User> {
	@Override
	public User validate(HttpServletRequest request) throws IncorrectFormDataException {
		User user = new User();
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				user.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		parameter = request.getParameter("login");
		if(parameter != null && !parameter.isEmpty()) {
			user.setLogin(parameter);
		}
		parameter = request.getParameter("password");
		if(parameter != null && !parameter.isEmpty()) {
			user.setPassword(parameter);
		} else {
			throw new IncorrectFormDataException("password", parameter);
		}
		parameter = request.getParameter("name");
		if(parameter != null && !parameter.isEmpty()) {
			user.setName(parameter);
		} else {
			throw new IncorrectFormDataException("name", parameter);
		}
		parameter = request.getParameter("surname");
		if(parameter != null && !parameter.isEmpty()) {
			user.setSurname(parameter);
		} else {
			throw new IncorrectFormDataException("surname", parameter);
		}
		parameter = request.getParameter("email");
		if(parameter != null && !parameter.isEmpty()) {
			user.setEmail(parameter);
		} else {
			throw new IncorrectFormDataException("email", parameter);
		}
		parameter = request.getParameter("district");
		if(parameter != null && !parameter.isEmpty()) {
			log.debug(String.format("district: %s", parameter));
			user.setDistrict(parameter);
		} else {
			throw new IncorrectFormDataException("district", parameter);
		}
		parameter = request.getParameter("mobilePhone");
		if(parameter != null && !parameter.isEmpty() && parameter.length() == 13) {
			user.setMobilePhone(parameter);
		} else {
			throw new IncorrectFormDataException("mobilePhone", parameter);
		}
		parameter = request.getParameter("role");
		if(parameter != null) {
			try {
				user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("role", parameter);
			}
		}
		return user;
	}
}
