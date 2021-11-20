package by.training.carservicebook.validator;

import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

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
		} else {
			throw new IncorrectFormDataException("login", parameter);
		}
		parameter = request.getParameter("role");
		try {
			user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
		} catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new IncorrectFormDataException("role", parameter);
		}
		return user;
	}
}
