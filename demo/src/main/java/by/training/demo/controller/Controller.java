package by.training.demo.controller;

import by.training.demo.controller.command.Command;
import by.training.demo.controller.command.CommandProvider;
import by.training.demo.exception.CommandException;
import by.training.demo.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final String PARAM_DELIMETER = " ";

    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request) throws ControllerException {

        if (request == null || request.split(PARAM_DELIMETER).length < 2){
            logger.error("wrong command length");
            throw new ControllerException("wrong command length");
        }
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(PARAM_DELIMETER));
        executionCommand = provider.getCommand(commandName);

        String response;
        try {
            response = executionCommand.execute(request.substring(request.indexOf(PARAM_DELIMETER) + 1));
        } catch (CommandException e) {
            logger.error("error in Command");
            throw new ControllerException(e);
        }
        return response;
    }

}
