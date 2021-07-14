package by.training.task05.entity.observable;

import by.training.task05.service.exception.TriangleServiceException;

public interface Observable<T> {
    void notifyObserver(T t) throws TriangleServiceException;
    void removeObserver(int id) throws TriangleServiceException;
}
