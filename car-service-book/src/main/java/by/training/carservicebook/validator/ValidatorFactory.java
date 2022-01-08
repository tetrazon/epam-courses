package by.training.carservicebook.validator;

import by.training.carservicebook.entity.*;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ValidatorFactory {
	private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> VALIDATORS = new HashMap<>();

	static {
		VALIDATORS.put(User.class, UserValidator.class);
		VALIDATORS.put(Car.class, CarValidator.class);
		VALIDATORS.put(CarRecord.class, CarRecordValidator.class);
		VALIDATORS.put(Offer.class, OfferValidator.class);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Entity> Validator<T> createValidator(Class<T> entity) {
		try {
			return (Validator<T>) VALIDATORS.get(entity).getDeclaredConstructor().newInstance();
		} catch(InstantiationException |
				IllegalAccessException |
				NoSuchMethodException |
				InvocationTargetException e) {
			log.error("create validator error", e);
			return null;
		}
	}
}
