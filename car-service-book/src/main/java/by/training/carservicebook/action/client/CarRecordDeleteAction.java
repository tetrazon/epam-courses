package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CarRecordDeleteAction extends ClientAction {

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car_record/list.html");
		try {
			CarRecordService service = factory.getService(CarRecordService.class);
			Integer carRecordId = Integer.parseInt(request.getParameter("carRecordId"));
			CarRecord carRecord = service.findById(carRecordId);
			if(carRecord != null) {
				service.delete(carRecord);
				forward.getAttributes().put("message", "Запись ТО удалена");
				log.info(String.format("User \"%s\" deleted carRecord with id %d", getAuthorizedUser().getLogin(), carRecordId));
			}
		} catch(NumberFormatException e) {
			log.warn(String.format("Incorrect data was found when user \"%s\" tried to delete carRecord", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
