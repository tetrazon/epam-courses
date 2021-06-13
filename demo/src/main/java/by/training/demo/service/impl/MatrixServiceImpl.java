package by.training.demo.service.impl;

import by.training.demo.dao.MatrixDao;
import by.training.demo.dao.factory.DaoFactory;
import by.training.demo.entity.Matrix;
import by.training.demo.exception.DaoException;
import by.training.demo.exception.ServiceException;
import by.training.demo.service.MatrixService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class MatrixServiceImpl implements MatrixService {
    private static final Logger logger = LogManager.getLogger(MatrixServiceImpl.class);
    private static final String NOT_FOUND = "not found!";

    private MatrixDao matrixDao = DaoFactory.getInstance().getMatrixDao();

    @Override
    public int create(int[][] intMatrix) {
        return 0;
    }

    @Override
    public int createRandom(String stringColumns,
                            String stringRows,
                            String stringMinValue,
                            String stringMaxValue) throws ServiceException {

        int columns = parseInt(stringColumns);
        int rows = parseInt(stringRows);
        int minValue = parseInt(stringMinValue);
        int maxValue = parseInt(stringMaxValue);

        if (columns < 1 || rows < 1 || (minValue > maxValue)){
            logger.error("wrong params");
            throw new ServiceException("wrong params");
        }
        try {
            return matrixDao.createRandom(columns, rows, minValue, maxValue);
        } catch (DaoException e) {
            //log: exception impossible
        }
        return -1;
    }

    @Override
    public int createFromFile(String fileName) throws ServiceException {
        filenameCheck(fileName);
        try {
            return matrixDao.createFromFile(fileName);
        } catch (DaoException e) {
            logger.error("error creation from file", e);
            throw new ServiceException("can't create from file", e);

        }
    }

    @Override
    public String read(String stringIndex) throws ServiceException {
        int index = parseInt(stringIndex);
        Optional<Matrix> optionalMatrix;
        try {
            optionalMatrix = matrixDao.read(index);
        } catch (DaoException e) {
            logger.error("error reading matrix", e);
            throw new ServiceException("can't read matrix by index " + index, e);
        }

        if (optionalMatrix.isPresent()){
            return optionalMatrix.get().toString();
        }
        return NOT_FOUND;
    }

    @Override
    public String add(String stringIndexA, String stringIndexB) throws ServiceException {
        int indexA = parseInt(stringIndexA);
        int indexB = parseInt(stringIndexB);
        try {
            return matrixDao.add(indexA, indexB).toString();
        } catch (DaoException e) {
            logger.error("error during add", e);
            throw new ServiceException("Error during add", e);
        }
    }

    @Override
    public String multiply(String stringIndexA, String stringIndexB) throws ServiceException {
        int indexA = parseInt(stringIndexA);
        int indexB = parseInt(stringIndexB);
        try {
            return matrixDao.multiply(indexA, indexB).toString();
        } catch (DaoException e) {
            logger.error("error multiply", e);
            throw new ServiceException("can't multiply", e);
        }

    }

    @Override
    public String subtract(String stringIndexA, String stringIndexB) throws ServiceException {
        int indexA = parseInt(stringIndexA);
        int indexB = parseInt(stringIndexB);
        try {
            return matrixDao.subtract(indexA, indexB).toString();
        } catch (DaoException e) {
            logger.error("can't subtract", e);
            throw new ServiceException("subtract error", e);
        }
    }

    @Override
    public String transpose(String stringIndex) throws ServiceException, DaoException {
        int index = parseInt(stringIndex);
        try {
            return matrixDao.transpose(index).toString();
        } catch (DaoException e) {
            logger.error("can't transpose", e);
            throw new DaoException("transpose error", e);
        }
    }

    private int parseInt(String stringIndex) throws ServiceException {
        int index;
        try {
            index = Integer.parseInt(stringIndex);
        } catch (NumberFormatException e){
            logger.error("parsing index error");
            throw new ServiceException("parsing index error", e);
        }
        return index;
    }

    private void filenameCheck(String fileName) throws ServiceException {
        if (fileName == null || fileName.isEmpty()){
            logger.error("empty/null filename");
            throw new ServiceException("empty/null filename");
        }
    }
}
