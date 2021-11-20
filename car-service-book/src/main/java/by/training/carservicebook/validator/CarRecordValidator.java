package by.training.carservicebook.validator;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.exception.IncorrectFormDataException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Log4j2
public class CarRecordValidator implements Validator<CarRecord> {
	@Override
	public CarRecord validate(HttpServletRequest request) throws IncorrectFormDataException {
		CarRecord carRecord = new CarRecord();
		String parameter = request.getParameter("kmInterval");
		if(parameter != null && !parameter.isEmpty()) {
			carRecord.setKmInterval(Integer.parseInt(parameter));
		} else {
			throw new IncorrectFormDataException("kmInterval", parameter);
		}
		parameter = request.getParameter("monthsInterval");
		if(parameter != null) {
			try {
				carRecord.setMonthInterval(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("monthsInterval", parameter);
			}
		}
		parameter = request.getParameter("description");
		if(parameter != null) {
			try {
				carRecord.setDescription(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("description", parameter);
			}
		}
		parameter = request.getParameter("id");
		log.debug("carRecord id:" + parameter);
		if(parameter != null) {
			try {
				carRecord.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		parameter = request.getParameter("carId");
		if(parameter != null) {
			try {
				Car car = new Car(Integer.parseInt(parameter));
				carRecord.setCar(car);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("carId", parameter);
			}
		}
		parameter = request.getParameter("isPeriodic");
		if(parameter != null) {
			try {
				carRecord.setIsPeriodic(Boolean.parseBoolean(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("isPeriodic", parameter);
			}
		}
		parameter = request.getParameter("isTender");
		if(parameter != null) {
			try {
				carRecord.setIsTender(Boolean.parseBoolean(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("isTender", parameter);
			}
		}
		parameter = request.getParameter("recordDate");
		if(parameter != null) {
			try {
				carRecord.setRecordDate(Date.valueOf(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("date", parameter);
			}
		}
		parameter = request.getParameter("category");
		if(parameter != null) {
			try {
				carRecord.setCategory(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("category", parameter);
			}
		}
		parameter = request.getParameter("workPrice");
		if(parameter != null) {
			try {
				carRecord.setWorkPrice(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("workPrice", parameter);
			}
		}
		return carRecord;
	}
}
