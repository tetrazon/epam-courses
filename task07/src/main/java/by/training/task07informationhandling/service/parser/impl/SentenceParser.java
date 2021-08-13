package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import by.training.task07informationhandling.service.parser.Handler;

import java.util.Arrays;
import java.util.List;

public class SentenceParser implements Handler {
    private static final String DELIMITER = "[\\s]";

    private Handler nextHandler;
    private TypeManager type;

    public SentenceParser(Handler nextHandler, TypeManager type) {
        this.nextHandler = nextHandler;
        this.type = type;
    }

    @Override
    public void handleRequest(Component component, String string) {
        List<String> lexemeList = Arrays.asList(string.trim().split(DELIMITER));
        for (String str : lexemeList) {
            System.out.println("lexeme:\n" + str);
            Component newComponent = new Composite(type);
            component.add(newComponent);
            nextHandler.handleRequest(newComponent, str);
        }

    }
}
