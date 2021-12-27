package by.training.carservicebook.action.master;

import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.exception.IncorrectFormDataException;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.OfferService;
import by.training.carservicebook.service.exception.ServiceException;
import by.training.carservicebook.validator.Validator;
import by.training.carservicebook.validator.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CarRecordDoneAction extends MasterAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car_record/approved.html");
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
			if(user != null && user.getRole().equals(Role.MASTER)) {
				Integer carRecordId = Integer.valueOf(request.getParameter("carRecordId"));
				Double price = Double.valueOf(request.getParameter("price"));
				CarRecord doneCarRecord = new CarRecord(carRecordId);
				doneCarRecord.setWorkPrice(price);
				doneCarRecord.setMaster(user);

				CarRecordService carRecordService = factory.getService(CarRecordService.class);
				carRecordService.markDone(doneCarRecord);

				OfferService offerService = factory.getService(OfferService.class);
				offerService.deleteByCarRecordId(carRecordId);

				forward.getAttributes().put("message", "Выполненная работа перемещена в архив");
			}
		return forward;
	}
}
