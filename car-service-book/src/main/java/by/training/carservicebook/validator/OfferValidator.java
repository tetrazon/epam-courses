package by.training.carservicebook.validator;

import by.training.carservicebook.entity.Car;
import by.training.carservicebook.entity.CarRecord;
import by.training.carservicebook.entity.Offer;
import by.training.carservicebook.exception.IncorrectFormDataException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class OfferValidator implements Validator<Offer> {
	@Override
	public Offer validate(HttpServletRequest request) throws IncorrectFormDataException {
		Offer offer = new Offer();
		String parameter = request.getParameter("carRecordId");
		log.debug("carRecordId:" + parameter);
		if(parameter != null) {
			try {
				Integer carRecordId = Integer.parseInt(parameter);
				CarRecord carRecord = new CarRecord(carRecordId);
				offer.setCarRecord(carRecord);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("carRecordId", parameter);
			}
		}
		parameter = request.getParameter("price");
		log.debug("price:" + parameter);
		if(parameter != null) {
			try {
				offer.setPrice(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("price", parameter);
			}
		}
		parameter = request.getParameter("isSelected");
		if(parameter != null) {
				offer.setIsSelected(Boolean.valueOf(parameter));
		} else {
			offer.setIsSelected(false);
		}

		return offer;
	}
}
