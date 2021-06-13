package by.training.demo.controller.command.impl;

import by.training.demo.controller.command.Command;
import by.training.demo.entity.CommandStatus;
import by.training.demo.exception.CommandException;
import by.training.demo.exception.ServiceException;
import by.training.demo.service.ArrayService;
import by.training.demo.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateIntegerArray implements Command {

    private static final Logger logger = LogManager.getLogger(CreateIntegerArray.class);

    private ArrayService arrayService = ServiceFactory.getInstance().getArrayService();

    @Override
    public String execute(String request) throws CommandException {
        int index = -1;
        try {
            index = arrayService.createInteger(request);
        } catch (ServiceException e) {
           logger.error("executing command error", e);
           throw new CommandException("executing command error", e);
        }
        return CommandStatus.PERFORMED.toString() + ", index of array: " + index;
    }
}
