package by.training.task05.entity.observer;

public interface Observer <T>{
    void handleEvent(T t);
    void removeObserver(int id);
}
