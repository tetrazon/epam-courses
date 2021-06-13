package by.training.demo.controller.command;

import by.training.demo.controller.command.impl.*;
import by.training.demo.entity.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.training.demo.entity.CommandName.*;

public final class CommandProvider {
    private Logger logger = LogManager.getLogger(CommandProvider.class);
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CREATE_DOUBLE_ARRAY, new CreateDoubleArray());
        repository.put(CREATE_INTEGER_ARRAY, new CreateIntegerArray());
        repository.put(CREATE_DOUBLE_ARRAY_FROM_FILE, new CreateDoubleArrayFromFile());
        repository.put(CREATE_INTEGER_ARRAY_FROM_FILE, new CreateIntegerArrayFromFile());
        repository.put(DELETE_ARRAY, new DeleteArray());
        repository.put(READ_ARRAY, new ReadArray());
        repository.put(SORT_ARRAY, new SortArray());
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
            if (command == null){
                throw new NullPointerException();
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error("wrong command");
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
