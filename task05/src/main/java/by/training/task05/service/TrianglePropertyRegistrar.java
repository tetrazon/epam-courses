package by.training.task05.service;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TriangleProperty;
import by.training.task05.entity.observer.Observer;
import by.training.task05.repository.Repository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrianglePropertyRegistrar implements Observer<Triangle> {

    private Repository<TriangleProperty> repository;

    @Override
    public void handleEvent(Triangle triangle) {
        TriangleProperty triangleProperty = new TriangleProperty(triangle);
        repository.update(triangle.getId(), triangleProperty);
    }

    @Override
    public void removeObserver(int id) {
        repository.delete(id);
    }
}
