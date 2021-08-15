package by.training.task07informationhandling.entity.composite.impl;

import by.training.task07informationhandling.entity.composite.Component;

import java.util.List;

public class Symbol implements Component {

    private char ch;

    public Symbol(char ch) {
        this.ch = ch;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Component> getChildList() {
        throw new UnsupportedOperationException("Cannot call getChildList in Symbol.class");
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public String collect() {
        return String.valueOf(ch);
    }
}
