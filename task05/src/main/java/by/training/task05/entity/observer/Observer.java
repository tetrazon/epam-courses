package by.training.task05.entity.observer;

import by.training.task05.service.exception.TriangleServiceException;

public interface Observer <T>{
    void handleEvent(T t) throws TriangleServiceException;
    void removeObserver(int id);
}
