package by.training.task05.service;

import by.training.task05.entity.Triangle;
import by.training.task05.service.exception.TriangleServiceException;
import by.training.task05.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface TriangleService {
    Optional<Triangle> read(int id) throws TriangleServiceException;
    int create(Triangle.Point a, Triangle.Point b, Triangle.Point c) throws TriangleServiceException;
    void createFromFile(String filename) throws TriangleServiceException;
    void update(int id, Triangle.Point a, Triangle.Point b, Triangle.Point c) throws TriangleServiceException;
    void delete(int id) throws TriangleServiceException;
    String getProperties(int id) throws TriangleServiceException;
    List<Triangle> readAll();
    List<Triangle> getBySpec(Specification specification) throws TriangleServiceException;
}
