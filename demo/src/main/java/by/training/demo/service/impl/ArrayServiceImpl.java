package by.training.demo.service.impl;

import by.training.demo.dao.ArrayDao;
import by.training.demo.dao.factory.DaoFactory;
import by.training.demo.entity.Array;
import by.training.demo.exception.DaoException;
import by.training.demo.exception.ServiceException;
import by.training.demo.service.ArrayService;
import by.training.demo.service.sorting.ArraySorting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ArrayServiceImpl implements ArrayService {

    private static Logger logger = LogManager.getLogger(ArrayServiceImpl.class);
    private final String paramDelimeter = " ";

    private ArrayDao arrayDaoImpl = DaoFactory.getInstance().getArrayDao();

    /**
     * @param stringArray integers separated by delimeter
     * @return index of created aray
     * @throws ServiceException
     */
    @Override
    public int createInteger(String stringArray) throws ServiceException {
        if (stringArray == null || stringArray.isEmpty()){
          logger.error("null/empty array storage attempt");
          throw new ServiceException("null/empty array storage attempt");
        }
        String[] temp = stringArray.split(paramDelimeter);
        int length = temp.length;
        Integer[] integers = new Integer[length];

        for (int i = 0; i < length; i++) {
            try {
                integers[i] = Integer.valueOf(temp[i]);
            } catch (NumberFormatException e){
                logger.error("parse Integer error", e);
                throw new ServiceException("Parse integer error", e);
            }

        }

        return arrayDaoImpl.create(new Array(integers));
    }

    /**
     *
     * @param stringArray doubles separated by delimeter
     * @return index of created aray
     * @throws ServiceException
     */
    @Override
    public int createDouble(String stringArray) throws ServiceException {
        if (stringArray == null || stringArray.isEmpty()){
            logger.error("null/empty array storage attempt");
            throw new ServiceException("null/empty array storage attempt");
        }
        String[] temp = stringArray.split(paramDelimeter);
        int length = temp.length;
        Double[] doubles = new Double[length];

        for (int i = 0; i < length; i++) {
            try {
                doubles[i] = Double.valueOf(temp[i]);
            } catch (NumberFormatException e){
                logger.error("parse Integer error", e);
                throw new ServiceException("Parse integer error", e);
            }
        }
        return arrayDaoImpl.create(new Array(doubles));
    }

    @Override
    public int createIntegerFromFile(String fileName) throws ServiceException {
        filenameCheck(fileName);
        try {
            return arrayDaoImpl.createIntegerFromFile(fileName);
        } catch (DaoException e) {
            logger.error("creating Integer array error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int createDoubleFromFile(String fileName) throws ServiceException {
        filenameCheck(fileName);
        int index = -1;
        try {
            index = arrayDaoImpl.createDoubleFromFile(fileName);
        } catch (DaoException e){
            logger.error("creating Double array error", e);
            throw new ServiceException(e);
        }
        return index;
    }

    /**
     *
     * @param stringIndex index of array to read
     * @return Optional<Array> in case of null element
     * @throws ServiceException
     */
    @Override
    public Optional<Array> read(String stringIndex) throws ServiceException {
        int index = parseIndex(stringIndex);
        Optional result;
        try {
             result = arrayDaoImpl.read(index);
        } catch (DaoException e){
            logger.error("reading array error", e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void delete(String stringIndex) throws ServiceException {
        int index = parseIndex(stringIndex);
        try {
            arrayDaoImpl.delete(index);
        } catch (DaoException e) {
            logger.error("deleting array error");
            throw new ServiceException(e);
        }
    }

    /**
     *
     * @param arraySorting kind of sorting @see SortName.class
     * @param arrayIndex index array to sort
     * @param isAscending true if is ascending
     * @return array.toString()
     * @throws ServiceException
     */
    @Override
    public String sort(ArraySorting arraySorting, String arrayIndex, boolean isAscending) throws ServiceException {
        if (arraySorting == null){
            throw new ServiceException("Sorting parameter error");
        }
        Array array = read(arrayIndex).orElseThrow(() -> new ServiceException("null array by index"));
        arraySorting.sort(array, isAscending);
        return array.toString();
    }

    private void filenameCheck(String fileName) throws ServiceException {
        if (fileName == null || fileName.isEmpty()){
            logger.error("empty/null filename");
            throw new ServiceException("empty/null filename");
        }
    }

    private int parseIndex(String stringIndex) throws ServiceException {
        int index;
        try {
            index = Integer.parseInt(stringIndex);
        } catch (NumberFormatException e){
            logger.error("parsing index error");
            throw new ServiceException("parsing index error", e);
        }
        return index;
    }
}
