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
import java.util.stream.Collectors;

@Log4j2
public class OfferListAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Integer carId = (Integer) request.getSession(false).getAttribute("carId");
		try{
			if (carId == null){
				carId = Integer.parseInt(request.getParameter("carId"));
			}
		} catch (NumberFormatException e){}
		if(carId != null) {
			log.debug(String.format("car id: %s", carId));
			CarService carService = factory.getService(CarService.class);
			Car car = carService.getCarById(carId);
			if (car != null){
				request.getSession().setAttribute("carModel", car.getModel());
			}
			CarRecordService carRecordService = factory.getService(CarRecordService.class);
			List<CarRecord> carRecordList = carRecordService.findCarRecordByCarId(carId);
			carRecordList = carRecordList
					.parallelStream()
							.filter(carRecord -> carRecord.getIsTender())
							.collect(Collectors.toList());
			log.debug(String.format("carRecordList: %s", carRecordList));

			OfferService offerService = factory.getService(OfferService.class);
			List<Offer> offerList = offerService.getOffersByCarRecords(carRecordList);
			UserService userService = factory.getService(UserService.class);
			offerList.forEach(offer -> {
				try {
					offer.setMaster(userService.findById(offer.getMaster().getId()));
				} catch (ServiceException e) {
					log.error("setting master error", e);
				}
			});
			request.getSession().setAttribute("offerList", offerList);
		}
		return null;
	}
}
