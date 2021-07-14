package by.training.task05.service;

import by.training.task05.entity.TriangleProperty;
import by.training.task05.service.exception.TrianglePropertyServiceException;

public interface TrianglePropertyService {
    TriangleProperty readPropertiesById(int id) throws TrianglePropertyServiceException;
}
