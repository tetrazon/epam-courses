package by.training.task05.repository;

import by.training.task05.repository.exception.TriangleRepositoryException;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> read(int id);
    List<T> readAll();
    int create(T t) throws TriangleRepositoryException;
    void update(int id, T t) throws TriangleRepositoryException;
    void delete(int id);
}
