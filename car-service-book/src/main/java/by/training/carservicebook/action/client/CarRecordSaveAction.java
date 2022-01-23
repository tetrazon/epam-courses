package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CarRecordSaveAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car_record/list.html");
		Validator<CarRecord> validator = ValidatorFactory.createValidator(CarRecord.class);
		final User user = getAuthorizedUser();
		try {
			final CarRecord carRecord = validator.validate(request);
			log.debug("save carRecord action");
			CarRecordService service = factory.getService(CarRecordService.class);
			final Integer carRecordId = service.save(carRecord);
			log.debug(String.format("user %s saved carRecord with id: %s", user.getLogin() ,carRecordId));
			forward.getAttributes().put("carRecordId", carRecordId);
			forward.getAttributes().put("carId", Integer.parseInt(request.getParameter("carId")));
			forward.getAttributes().put("message", "message.saveCarRecord");

		} catch (IncorrectFormDataException e) {
			forward.getAttributes().put("message", "message.incorrectData");
			log.warn(String.format("Incorrect data was found when user \"%s\" tried to save carRecord", user.getLogin()), e);
		}
		return forward;
	}
}
