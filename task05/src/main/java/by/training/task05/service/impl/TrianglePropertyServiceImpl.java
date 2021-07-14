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


    @Override
    public TriangleProperty readPropertiesById(int id) throws TrianglePropertyServiceException {
        if (id < 0){
            throw new TrianglePropertyServiceException("incorrect id");
        }
        final Optional<TriangleProperty> optionalTriangleProperty = repository.read(id);
        return optionalTriangleProperty.orElseThrow(()-> new TrianglePropertyServiceException("null element by that id"));
    }
}
