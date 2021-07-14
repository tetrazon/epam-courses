package by.training.task05.service.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.observable.Observable;
import by.training.task05.repository.exception.TriangleRepositoryException;
import by.training.task05.service.exception.TrianglePropertyServiceException;
import by.training.task05.service.parser.TriangleParser;
import by.training.task05.repository.TriangleRepository;
import by.training.task05.service.TrianglePropertyRegistrar;
import by.training.task05.service.TrianglePropertyService;
import by.training.task05.service.TriangleService;
import by.training.task05.service.exception.TriangleServiceException;
import by.training.task05.specification.Specification;
import by.training.task05.validator.TriangleValidator;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TriangleServiceImpl implements TriangleService, Observable<Triangle> {

    private TriangleRepository<Triangle> triangleRepository;
    private TrianglePropertyRegistrar registrar;
    private TrianglePropertyService trianglePropertyService;
    private TriangleValidator triangleValidator = TriangleValidator.getInstance();

    public TriangleServiceImpl(TriangleRepository<Triangle> triangleRepository,
                               TrianglePropertyRegistrar registrar,
                               TrianglePropertyService trianglePropertyService) {
        this.triangleRepository = triangleRepository;
        this.registrar = registrar;
        this.trianglePropertyService = trianglePropertyService;
    }

    @Override
    public Optional<Triangle> read(int id) throws TriangleServiceException {
        if (id <= 0){
            throw new TriangleServiceException("incorrect id");
        }
        return triangleRepository.read(id);
    }

    @Override
    public int create(Triangle.Point a, Triangle.Point b, Triangle.Point c) throws TriangleServiceException {
        Triangle triangle = new Triangle(a, b, c);
        int id;
        if (triangleValidator.isTriangle(triangle)){
            try {
                id = triangleRepository.create(triangle);
            } catch (TriangleRepositoryException e) {
                throw new TriangleServiceException("Wrong id",e);
            }
            notifyObserver(triangleRepository.read(id)
                    .orElseThrow(() -> new TriangleServiceException("error reading from repository")));

            return id;
        } else {
            throw new TriangleServiceException("Not valid triangle");
        }
    }

    @Override
    public void createFromFile(String filename) throws TriangleServiceException {
        final List<Triangle> triangleList = TriangleParser.getInstance().parseTrianglesFromFile(filename);
        int id;
        for (Triangle triangle : triangleList) {
            if (triangleValidator.isTriangle(triangle)) {
                try {
                    id = triangleRepository.create(triangle);
                } catch (TriangleRepositoryException e) {
                    throw new TriangleServiceException("Wrong id",e);
                }
                notifyObserver(triangleRepository.read(id).orElseThrow(() -> new TriangleServiceException("error reading from repository")));
            }
        }
    }

    @Override
    public void update(int id, Triangle.Point a, Triangle.Point b, Triangle.Point c) throws TriangleServiceException {
        if (id <= 0){
            throw new TriangleServiceException("incorrect id");
        }
        if (a == null || b == null || c == null){
            throw new TriangleServiceException("null points");
        }
        Triangle triangle = new Triangle(a, b, c);
        triangle.setId(id);
        if (triangleValidator.isTriangle(triangle)){
            try {
                triangleRepository.update(id, triangle);
            } catch (TriangleRepositoryException e) {
                throw new TriangleServiceException("error update",e);
            }
            notifyObserver(triangle);
        } else {
            throw new TriangleServiceException("Not valid triangle");
        }
    }

    @Override
    public void delete(int id) throws TriangleServiceException {
        if (id <= 0){
            throw new TriangleServiceException("incorrect id");
        }
        triangleRepository.delete(id);
        removeObserver(id);
    }

    @Override
    public String getProperties(int id) throws TriangleServiceException {
        try {
            return trianglePropertyService.readPropertiesById(id).toString();
        } catch (TrianglePropertyServiceException e) {
            throw new TriangleServiceException("error setting props",e);
        }
    }

    @Override
    public List<Triangle> readAll() {
        return triangleRepository.readAll();
    }

    @Override
    public List<Triangle> getBySpec(Specification specification) throws TriangleServiceException {
        if (specification == null){
            throw new TriangleServiceException("Null specification");
        }
        return triangleRepository.getBySpec(specification);
    }

    @Override
    public void notifyObserver(Triangle triangle) throws TriangleServiceException {
       registrar.handleEvent(triangle);
    }

    @Override
    public void removeObserver(int id) throws TriangleServiceException {
        if (id <= 0){
            throw new TriangleServiceException("incorrect id");
        }
        registrar.removeObserver(id);
    }

}
