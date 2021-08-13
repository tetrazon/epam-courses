package by.training.task07informationhandling.service.parser;

import by.training.task07informationhandling.entity.composite.Component;

public interface Handler {
    void handleRequest(Component component, String string);
}
