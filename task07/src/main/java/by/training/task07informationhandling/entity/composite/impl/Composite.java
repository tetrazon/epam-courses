package by.training.task07informationhandling.entity.composite.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.manager.TypeManager;

import java.util.LinkedList;
import java.util.List;

public class Composite implements Component {
    //TODO equals hashcode

    private TypeManager type;
    private List<Component> componentList = new LinkedList<>();

    public Composite(Composite composite){
        type = composite.type;
        for (Component component : composite.componentList) {
            componentList.add(new Composite((Composite) component));
        }

    }
    public Composite(TypeManager type) {
        this.type = type;
    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public Component getChild(int index) {
        return componentList.get(index);
    }

    @Override
    public List<Component> getChildList() {
        return componentList;
    }

    @Override
    public void remove(int index) {
        componentList.remove(index);
    }

    @Override
    public String collect() {
        StringBuilder sb = new StringBuilder();
        String tmpString;
        for (int i = 0; i < componentList.size(); i++) {
            Component component = getChild(i);
            tmpString = component.collect();
            if (type == TypeManager.SENTENCE  && i == componentList.size()-1) {
                sb.append(tmpString);
                continue;
            }
            sb.append(String.format(type.getFormat(), tmpString));
        }
        return sb.toString();
    }
}
