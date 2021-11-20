package by.training.carservicebook.action;

import by.training.carservicebook.entity.Role;

import java.util.Arrays;

public abstract class AuthorizedUserAction extends Action {
	public AuthorizedUserAction() {
		getAllowRoles().addAll(Arrays.asList(Role.values()));
	}
}
