package by.training.task05.entity.observable;

public interface Observable<T> {
    void notifyObserver(T t);
    void removeObserver(int id);
}
