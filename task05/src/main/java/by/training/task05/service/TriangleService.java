package by.training.task05.service;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface TriangleService {
    Optional<Triangle> read(int id);
    int create(Triangle.Point a, Triangle.Point b, Triangle.Point c);
    void createFromFile(String filename);
    void update(int id, Triangle.Point a, Triangle.Point b, Triangle.Point c);
    void delete(int id);
    String getProperties(int id);
    List<Triangle> readAll();
    List<Triangle> getBySpec(Specification specification);
}
