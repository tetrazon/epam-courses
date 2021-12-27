package by.training.carservicebook.action.master;

import by.training.carservicebook.action.Action;
import by.training.carservicebook.entity.Role;

abstract public class MasterAction extends Action {
	public MasterAction() {
		getAllowRoles().add(Role.MASTER);
	}
}
