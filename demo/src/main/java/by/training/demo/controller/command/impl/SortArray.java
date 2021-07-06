package by.training.demo.controller.command.impl;

import by.training.demo.controller.command.Command;
import by.training.demo.exception.CommandException;
import by.training.demo.exception.ServiceException;
import by.training.demo.service.ArrayService;
import by.training.demo.service.factory.ServiceFactory;
import by.training.demo.service.sorting.ArraySorting;
import by.training.demo.service.sorting.ArraySortingProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortArray implements Command {
    private static final Logger logger = LogManager.getLogger(SortArray.class);

    private ArrayService arrayService = ServiceFactory.getInstance().getArrayService();
    @Override
    public String execute(String request) throws CommandException {
        String result;
        String[] requestArgs = request.split(" ");
        ArraySorting arraySorting = new ArraySortingProvider().getSorting(requestArgs[0]);
        String index = requestArgs[1];
        boolean isAscending = Boolean.parseBoolean(requestArgs[2]);

        try {
            result = arrayService.sort(arraySorting, index, isAscending);
        } catch (ServiceException e) {
            logger.error("error in Service layer", e);
            throw new CommandException("executing command error", e);
        }
        return result;
    }
}
