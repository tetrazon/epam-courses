package by.training.carservicebook.action.master;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Role;
import by.training.carservicebook.entity.User;
import by.training.carservicebook.service.CarRecordService;
import by.training.carservicebook.service.CarService;
import by.training.carservicebook.service.UserService;
import by.training.carservicebook.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class CarRecordTenderListAction extends MasterAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		final User user = getAuthorizedUser();
		log.debug(String.format("user: %s", user));
		if(user != null && user.getRole().equals(Role.MASTER)) {

			UserService userService = factory.getService(UserService.class);
			List<User> userList = userService.findByRoleAndDistrict(Role.CLIENT, user.getDistrict());
			log.debug(String.format("userList: %s", userList));

			CarService carService = factory.getService(CarService.class);
			List<Car> carList = new ArrayList<>();
			for (User u : userList) {
				try {
					carList.addAll(carService.getCarByUserId(u.getId()));
				} catch (ServiceException e) {
					log.warn(String.format("Incorrect data was found when trying get cars for iserId: \"%s\" ", u.getId()), e);
				}
			}
			log.debug(String.format("carList: %s", carList));

			CarRecordService carRecordService = factory.getService(CarRecordService.class);
			List<CarRecord> carRecordList = new ArrayList<>();
			for (Car car : carList) {
				car.setCarRecords(carRecordService.findByCarIdAndTender(car.getId(), true));
			}
			carList = carList.stream()
					.filter(car -> !car.getCarRecords().isEmpty())
							.collect(Collectors.toList());
			request.getSession().setAttribute("carList", carList);
		}
		return null;
	}
}
