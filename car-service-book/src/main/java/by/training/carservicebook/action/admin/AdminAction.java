package by.training.carservicebook.action.admin;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.Role;

abstract public class AdminAction extends Action {
	public AdminAction() {
		getAllowRoles().add(Role.ADMIN);
	}
}
