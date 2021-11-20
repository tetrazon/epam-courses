package by.training.carservicebook.validator;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.exception.IncorrectFormDataException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class CarValidator implements Validator<Car> {
	@Override
	public Car validate(HttpServletRequest request) throws IncorrectFormDataException {
		Car car = new Car();
		String parameter = request.getParameter("model");
		if(parameter != null && !parameter.isEmpty()) {
			car.setModel(parameter);
		} else {
			throw new IncorrectFormDataException("model", parameter);
		}
		parameter = request.getParameter("mileage");
		if(parameter != null) {
			try {
				car.setMileage(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("mileage", parameter);
			}
		}
		parameter = request.getParameter("year");
		if(parameter != null) {
			try {
				car.setYear(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("year", parameter);
			}
		}
		parameter = request.getParameter("id");
		log.debug("car id:" + parameter);
		if(parameter != null) {
			try {
				car.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		return car;
	}
}
