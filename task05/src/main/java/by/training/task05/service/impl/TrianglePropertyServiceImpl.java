package by.training.task05.service.impl;

import by.training.task05.entity.TriangleProperty;
import by.training.task05.repository.Repository;
import by.training.task05.service.TrianglePropertyService;
import by.training.task05.service.exception.TrianglePropertyServiceException;

import java.util.Optional;

public class TrianglePropertyServiceImpl implements TrianglePropertyService {

    private Repository<TriangleProperty> repository;

    public TrianglePropertyServiceImpl(Repository<TriangleProperty> trianglePropertyRepository) {
        repository = trianglePropertyRepository;
    }


    private void checkId(int id){
        if (id < 0){
            throw new TrianglePropertyServiceException("incorrect id");
        }
    }

    @Override
    public TriangleProperty readPropertiesById(int id) {
        checkId(id);
        final Optional<TriangleProperty> optionalTriangleProperty = repository.read(id);
        return optionalTriangleProperty.orElseThrow(()-> new TrianglePropertyServiceException("wrong id"));
    }
}
