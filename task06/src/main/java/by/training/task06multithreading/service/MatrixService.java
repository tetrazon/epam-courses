package by.training.task06multithreading.service;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.exception.MatrixServiceException;

public interface MatrixService {
    Matrix readFromFile(String filename) throws MatrixServiceException;
    void setZerosInDiagonal(Matrix matrix) throws MatrixServiceException;
}
