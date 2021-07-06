package by.training.task05.service.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.observable.Observable;
import by.training.task05.parser.TriangleParser;
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
    public Optional<Triangle> read(int id) {
        checkId(id);
        return triangleRepository.read(id);
    }

    @Override
    public int create(Triangle.Point a, Triangle.Point b, Triangle.Point c) {
        Triangle triangle = new Triangle(a, b, c);
        int id;
        if (triangleValidator.isTriangle(triangle)){
             id = triangleRepository.create(triangle);
            notifyObserver(triangleRepository.read(id)
                    .orElseThrow(() -> new TriangleServiceException("error reading from repository")));

            return id;
        } else {
            throw new TriangleServiceException("Not valid triangle");
        }
    }

    @Override
    public void createFromFile(String filename) {
        final List<Triangle> triangleList = TriangleParser.getInstance().parseTrianglesFromFile(filename);
        int id;
        for (Triangle triangle : triangleList) {
            if (triangleValidator.isTriangle(triangle)) {
                id = triangleRepository.create(triangle);
                notifyObserver(triangleRepository.read(id).orElseThrow(() -> new TriangleServiceException("error reading from repository")));
            }
        }
    }

    @Override
    public void update(int id, Triangle.Point a, Triangle.Point b, Triangle.Point c) {
        checkId(id);
        if (a == null || b == null || c == null){
            throw new TriangleServiceException("null points");
        }
        Triangle triangle = new Triangle(a, b, c);
        triangle.setId(id);
        if (triangleValidator.isTriangle(triangle)){
            triangleRepository.update(id, triangle);
            notifyObserver(triangle);
        } else {
            throw new TriangleServiceException("Not valid triangle");
        }
    }

    @Override
    public void delete(int id) {
        checkId(id);
        triangleRepository.delete(id);
        removeObserver(id);
    }

    @Override
    public String getProperties(int id) {
        return trianglePropertyService.readPropertiesById(id).toString();
    }

    @Override
    public List<Triangle> readAll() {
        return triangleRepository.readAll();
    }

    @Override
    public List<Triangle> getBySpec(Specification specification) {
        if (specification == null){
            throw new TriangleServiceException("Null specification");
        }
        return triangleRepository.getBySpec(specification);
    }

    @Override
    public void notifyObserver(Triangle triangle) {
       registrar.handleEvent(triangle);
    }

    @Override
    public void removeObserver(int id) {
        checkId(id);
        registrar.removeObserver(id);
    }

    private void checkId(int id){
        if (id <= 0){
            throw new TriangleServiceException("incorrect id");
        }
    }
}
