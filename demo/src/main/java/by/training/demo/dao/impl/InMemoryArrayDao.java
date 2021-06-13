package by.training.demo.dao.impl;

import by.training.demo.dao.ArrayDao;
import by.training.demo.entity.Array;
import by.training.demo.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryArrayDao implements ArrayDao {

    private static Logger logger = LogManager.getLogger(InMemoryArrayDao.class);
    private static final String PARAM_DELIMETER = " ";

    private List<Array> storage = new ArrayList<>();

    @Override
    public int createIntegerFromFile(String fileName) throws DaoException {
        Integer[] arr;
        String[] strings = getStrings(fileName);

        int length = strings.length;
        arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            try{
                arr[i] = Integer.valueOf(strings[i]);
            } catch (NumberFormatException e){
                logger.error("parsing double error", e);
                throw new DaoException(e);
            }
        }

        Array<Integer> newArray = new Array<>(arr);
        storage.add(newArray);
        return storage.indexOf(newArray);
    }

    @Override
    public int createDoubleFromFile(String fileName) throws DaoException {
        Double[] arr;
        String[] strings = getStrings(fileName);

        int length = strings.length;
        arr = new Double[length];
        for (int i = 0; i < length; i++) {
            try{
                arr[i] = Double.valueOf(strings[i]);
            } catch (NumberFormatException e){
                logger.error("parsing double error", e);
                throw new DaoException(e);
            }
        }

        Array<Double> newArray = new Array<>(arr);
        storage.add(newArray);
        return storage.indexOf(newArray);
    }

    @Override
    public int create(Array array) {
        storage.add(array);
        return storage.indexOf(array);
    }

    @Override
    public Optional<Array> read(int index) throws DaoException {
        Optional<Array> result = Optional.empty();
        if (isInRange(index)){
            result = Optional.ofNullable(storage.get(index));
        } else {
            logger.error("wrong index");
        }
        return result;
    }

    @Override
    public void delete(int index) throws DaoException {
        if (isInRange(index)){
            storage.set(index, null);
        } else {
            logger.error("wrong index");
            throw new DaoException("wrong index");
        }

    }

    private boolean isInRange(int index) {
        return index >= 0 && index < storage.size();
    }

    private String[] getStrings(String fileName) throws DaoException {
        String[] strings;
        try {
            strings = new String(Files.readAllBytes(Paths.get(fileName))).split(PARAM_DELIMETER);
        } catch (IOException e) {
            logger.error("Error file reading", e);
            throw new DaoException(e);
        }
        return strings;
    }
}
