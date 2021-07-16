package by.training.task06multithreading.service.impl;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.entity.exception.MatrixException;
import by.training.task06multithreading.service.MatrixService;
import by.training.task06multithreading.service.exception.MatrixServiceException;
import by.training.task06multithreading.service.parser.MatrixParser;
import by.training.task06multithreading.service.parser.exception.MatrixParserException;

public class MatrixServiceImpl implements MatrixService {
    @Override
    public Matrix readFromFile(String filename) throws MatrixServiceException {
        try {
            return MatrixParser.getInstance().parseMatrixFromFile("data/matrix.txt");
        } catch (MatrixParserException e) {
            throw new MatrixServiceException("creation matrix error", e);
        }
    }

    @Override
    public void setZerosInDiagonal(Matrix matrix) throws MatrixServiceException {
        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            try {
                matrix.setElement(i,i,0);
            } catch (MatrixException e) {
                throw new MatrixServiceException("error element setting", e);
            }
        }
    }
}
