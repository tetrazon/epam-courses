package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import by.training.task07informationhandling.service.parser.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Handler {

    private static final String DELIMITER = "[^.?!\\–,:;]+";

    private Handler nextHandler;
    private TypeManager type;

    public LexemeParser(Handler nextHandler, TypeManager type) {
        this.nextHandler = nextHandler;
        this.type = type;
    }

    @Override
    public void handleRequest(Component component, String string) {
        List<String> wordsList = new ArrayList<>();
        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(string);
        String word = string;
        if (matcher.find()){
            word = matcher.group();
        }
        wordsList.add(word);
        String punct = string.replace(wordsList.get(0), "");
        wordsList.add(punct);

        for (String s : wordsList) {
            System.out.println("Word:\n" + s);
            Component newComponent = new Composite(type);
            component.add(newComponent);
            nextHandler.handleRequest(newComponent, s);
        }
    }

}
