package by.training.demo.controller;

import by.training.demo.exception.ControllerException;
import by.training.demo.view.View;

public class Runner {
    public static void main(String[] args) throws ControllerException {
        View view = new View(new Controller());
        view.menu();

    }
}
