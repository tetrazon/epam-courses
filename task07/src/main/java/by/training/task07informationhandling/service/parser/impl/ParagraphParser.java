package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import by.training.task07informationhandling.service.parser.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Handler {

    private static final String DELIMITER = "\\s?[A-Za-z,;:'\"\\s\\(\\)\\-\\&\\<\\>\\|\\^\\~0-9]*((\\.{3})|(\\?\\!)|[.?!])";

    private Handler nextHandler;
    private TypeManager type;

    public ParagraphParser(Handler nextHandler, TypeManager type) {
        this.nextHandler = nextHandler;
        this.type = type;
    }

    @Override
    public void handleRequest(Component component, String string) {
        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
             String str = matcher.group();
            Component newComponent = new Composite(type);
            component.add(newComponent);
            nextHandler.handleRequest(newComponent, str.trim());
        }
    }
}
