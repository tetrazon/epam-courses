package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CarRecordEditAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		log.debug("carRecord edit action");
		try{
			Integer carRecordId = (Integer)request.getAttribute("carRecordId");
			if(carRecordId == null) {
				carRecordId = Integer.parseInt(request.getParameter("carRecordId"));
			}
			CarRecordService service = factory.getService(CarRecordService.class);
			CarRecord carRecord = service.findById(carRecordId);
			if (carRecord != null){
				request.setAttribute("carRecord", carRecord);
			}

		} catch (NumberFormatException e) {
		}
		return null;
	}
}
