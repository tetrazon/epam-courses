package by.training.task07informationhandling.entity.composite;

import java.util.List;

public interface Component {
    void add(Component component);
    Component getChild(int index);
    List<Component> getChildList();
    void remove(int index);
    String collect();
}
