package by.training.carservicebook.action.client;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.Role;

abstract public class ClientAction extends Action {
	public ClientAction() {
		getAllowRoles().add(Role.CLIENT);
	}
}
