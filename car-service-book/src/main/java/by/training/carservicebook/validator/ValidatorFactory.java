package by.training.carservicebook.validator;

import by.training.carservicebook.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {
	private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

	static {
		validators.put(User.class, UserValidator.class);
		validators.put(Car.class, CarValidator.class);
		validators.put(CarRecord.class, CarRecordValidator.class);
		validators.put(Offer.class, OfferValidator.class);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Entity> Validator<T> createValidator(Class<T> entity) {
		try {
			return (Validator<T>)validators.get(entity).getDeclaredConstructor().newInstance();
		} catch(InstantiationException |
				IllegalAccessException |
				NoSuchMethodException |
				InvocationTargetException e) {
			return null;
		}
	}
}
