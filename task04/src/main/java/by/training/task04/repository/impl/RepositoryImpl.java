package by.training.task04.repository.impl;

import by.training.task04.repository.RepositoryException;
import by.training.task04.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * in memory String repository implementation
 */
public class RepositoryImpl implements Repository<String> {

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
        if (id < 0 || id > stringRepo.size() -1){
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
