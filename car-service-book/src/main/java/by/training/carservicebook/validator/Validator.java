package by.training.carservicebook.validator;


import by.training.carservicebook.entity.Entity;
import by.training.carservicebook.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T extends Entity> {
	T validate(HttpServletRequest request) throws IncorrectFormDataException;
}
