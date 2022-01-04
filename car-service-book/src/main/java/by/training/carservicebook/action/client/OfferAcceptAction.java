package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.OfferService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
public class OfferAcceptAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Forward forward = new Forward("/car_record/offer_list.html");
		Integer carRecordId = null;
		Integer masterId = null;
		try{
			carRecordId = Integer.parseInt(request.getParameter("carRecordId"));
			masterId = Integer.parseInt(request.getParameter("masterId"));
		} catch (NumberFormatException e){}
		if(carRecordId != null && masterId != null) {
			log.debug(String.format("carRecordId : %s", carRecordId));
			log.debug(String.format("masterId : %s", masterId));

			CarRecordService carRecordService = factory.getService(CarRecordService.class);
			CarRecord carRecord = carRecordService.findById(carRecordId);
				carRecord.setIsTender(false);
				carRecordService.save(carRecord);

			OfferService offerService = factory.getService(OfferService.class);
			Offer offer = offerService.getByRecordIdAndMasterId(carRecordId, masterId);
			offer.setIsSelected(true);
			offerService.save(offer);

			request.getSession().setAttribute("message", "предложение выбрано, ждите обратной связи от мастера");
		}
		return forward;
	}
}
