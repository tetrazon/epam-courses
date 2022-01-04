package by.training.carservicebook.action.client;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
public class CarRecordHistoryAction extends ClientAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Integer carId = Integer.parseInt(request.getParameter("carId"));
		if(carId != null) {
			log.debug(String.format("car id: %s", carId));
			CarService carService = factory.getService(CarService.class);
			Car car = carService.getCarById(carId);
			if (car != null){
				request.getSession().setAttribute("carModel", car.getModel());
				request.getSession().setAttribute("carId", carId);
				CarRecordService carRecordService = factory.getService(CarRecordService.class);
				List<CarRecord> carRecordList = carRecordService.findCarRecordHistoryByCarId(carId);
				UserService userService = factory.getService(UserService.class);
				carRecordList.forEach(carRecord -> {
					try {
						carRecord.setMaster(userService.findById(carRecord.getMaster().getId()));
					} catch (ServiceException e) {
						log.warn("cannot find user");
						carRecord.setMaster(null);
					}
				});
				log.debug(String.format("carRecordList: %s", carRecordList));
				request.getSession().setAttribute("carRecordList", carRecordList);
			}
		}
		return null;
	}
}
