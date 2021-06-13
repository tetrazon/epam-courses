package by.training.demo.controller.command.impl;

import by.training.demo.controller.command.Command;
import by.training.demo.entity.CommandStatus;
import by.training.demo.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WrongRequest implements Command {
    private static final Logger logger = LogManager.getLogger(WrongRequest.class);

    @Override
    public String execute(String request) throws CommandException {
        logger.error("wrong request: " + request);
        return CommandStatus.NOT_PERFORMED.toString();
    }
}
