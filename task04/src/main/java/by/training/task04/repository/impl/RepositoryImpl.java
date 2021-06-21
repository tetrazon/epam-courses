package by.training.task04.repository.impl;

import by.training.task04.exception.RepositoryException;
import by.training.task04.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * in memory String repository implementation
 */
public class RepositoryImpl implements Repository<String> {

    private static final Logger logger = LogManager.getLogger(RepositoryImpl.class);


    private List<String> stringRepo = new ArrayList<>();
    @Override
    public int create(String string) {
        stringRepo.add(string);
        return stringRepo.indexOf(string);
    }

    @Override
    public void remove(int id) {
        throwExceptionIfOutOfBand(id);
        stringRepo.set(id, null);

    }

    private void throwExceptionIfOutOfBand(int id) {
        if (id < 0 || id > stringRepo.size()){
            logger.error("index out of band");
            throw new RepositoryException("index out of band");
        }
    }

    @Override
    public Optional<String> getById(int id) {
        throwExceptionIfOutOfBand(id);
        return Optional.ofNullable(stringRepo.get(id));
    }

    @Override
    public void update(String newString, int id) {
        throwExceptionIfOutOfBand(id);
        stringRepo.set(id, newString);
    }
}
