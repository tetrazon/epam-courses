package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Symbol;
import by.training.task07informationhandling.service.parser.Handler;

public class WordParser implements Handler {

    @Override
    public void handleRequest(Component component, String string) {
        System.out.println(" word to ch: " + string);

        char[] chars = string.trim().toCharArray();
        for (char ch : chars) {
            System.out.println("char:\n" + ch);
            Component newComponent = new Symbol(ch);
            component.add(newComponent);
        }

    }
}
