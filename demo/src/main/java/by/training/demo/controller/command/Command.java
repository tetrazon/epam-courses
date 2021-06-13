package by.training.demo.controller.command;

import by.training.demo.exception.CommandException;

public interface Command {

    String execute(String request) throws CommandException;
}
