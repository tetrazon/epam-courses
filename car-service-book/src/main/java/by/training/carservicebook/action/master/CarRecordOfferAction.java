package by.training.carservicebook.action.master;

import by.training.carservicebook.entity.*;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.OfferService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class CarRecordOfferAction extends MasterAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car_record/tender_list.html");
		Validator<Offer> validator = ValidatorFactory.createValidator(Offer.class);
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
		try {
			if(user != null && user.getRole().equals(Role.MASTER)) {
				OfferService offerService = factory.getService(OfferService.class);
				//Integer carRecordId = (Integer) request.getAttribute("carRecordId");
				//Double price = (Double) request.getAttribute("price");
				//Offer offer = new Offer(carRecordId, user, price);
				Offer offer = validator.validate(request);
				offer.setMaster(user);
				offerService.save(offer);
				forward.getAttributes().put("message", "Предложение зафиксировано");
			}
		} catch (IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Предложение не зафиксировано, некорректные данные");
			log.warn(String.format("Incorrect data was found when user \"%s\" tried to make offer", user.getLogin()), e);
		}
		return forward;
	}
}
