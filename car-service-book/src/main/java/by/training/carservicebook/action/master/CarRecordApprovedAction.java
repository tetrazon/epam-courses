package by.training.carservicebook.action.master;

import by.training.carservicebook.entity.*;
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
public class CarRecordApprovedAction extends MasterAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
		if(user != null && user.getRole().equals(Role.MASTER)) {
			OfferService offerService = factory.getService(OfferService.class);
			List<Offer> offerList = offerService.getSelectedByMasterId(user.getId());
			log.debug(String.format("offerList: %s", offerList));
			CarRecordService carRecordService = factory.getService(CarRecordService.class);
			UserService userService = factory.getService(UserService.class);
			CarService carService = factory.getService(CarService.class);
			for (Offer offer : offerList) {
				CarRecord carRecord = carRecordService.findById(offer.getCarRecord().getId());
				Car carById = carService.getCarById(carRecord.getCar().getId());
				User userById = userService.findById(carById.getUser().getId());
				carById.setUser(userById);
				carRecord.setCar(carById);
				log.debug(String.format("carRecord: %s", carRecord));

				offer.setCarRecord(carRecord);
			}
			log.debug(String.format("offerList: %s", offerList));

			List<CarRecord> carRecordList = carRecordService.findByOffers(offerList);
			//log.debug(String.format("carRecordList: %s", carRecordList));
			request.getSession().setAttribute("offerList", offerList);
			//request.getSession().setAttribute("carList", carList);
		}
		return null;
	}
}
