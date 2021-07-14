package by.training.task05.service;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TriangleProperty;
import by.training.task05.entity.observer.Observer;
import by.training.task05.repository.Repository;
import by.training.task05.repository.exception.TriangleRepositoryException;
import by.training.task05.service.exception.TriangleServiceException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrianglePropertyRegistrar implements Observer<Triangle> {

    private Repository<TriangleProperty> repository;

    @Override
    public void handleEvent(Triangle triangle) throws TriangleServiceException {
        TriangleProperty triangleProperty = new TriangleProperty(triangle);
        try {
            repository.update(triangle.getId(), triangleProperty);
        } catch (TriangleRepositoryException e) {
            throw new TriangleServiceException("error handle", e);
        }
    }

    @Override
    public void removeObserver(int id) {
        repository.delete(id);
    }
}
