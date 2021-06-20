package by.training.task04.repository;

import java.util.Optional;

public interface Repository<T> {
    int create(T t);
    void remove(int id);
    Optional<T> getById(int id);
    void update(T t, int id);
}
