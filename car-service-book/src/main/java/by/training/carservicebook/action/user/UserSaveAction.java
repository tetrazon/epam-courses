package by.training.carservicebook.action.user;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class UserSaveAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/register.html");
        Validator<User> validator = ValidatorFactory.createValidator(User.class);
        try {
            final User user = validator.validate(request);
            log.debug("save user action");
            log.debug(String.format("user: %s", user));
            UserService service = factory.getService(UserService.class);
            if (service.existByLogin(user.getLogin()) && user.getId() == null){
                forward.getAttributes().put("message", "message.loginExist");
            } else {
                service.save(user);
                log.debug(String.format("user %s is registered ", user.getLogin()));
                forward.getAttributes().put("message", "message.userRegistered");
                if (user.getId() != null){
                    forward = new Forward("/logout.html");
                } else {
                    forward = new Forward("/login.html");
                }
            }
        } catch (IncorrectFormDataException e) {
            forward.getAttributes().put("message", "message.incorrectData");
            log.warn("Incorrect data was found while registering new user", e);
        }
        return forward;
    }
}
