package by.training.task05.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> read(int id);
    List<T> readAll();
    int create(T t);
    void update(int id, T t);
    void delete(int id);
}
