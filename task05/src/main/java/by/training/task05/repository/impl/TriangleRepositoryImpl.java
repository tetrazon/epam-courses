package by.training.task05.repository.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.repository.TriangleRepository;
import by.training.task05.repository.exception.TriangleRepositoryException;
import by.training.task05.specification.FindSpecification;
import by.training.task05.specification.SortSpecification;
import by.training.task05.specification.Specification;

import java.util.*;

public class TriangleRepositoryImpl implements TriangleRepository<Triangle> {
    private static int nextId = 1;

    private Map<Integer, Triangle> storage = new HashMap<>();

    @Override
    public Optional<Triangle> read(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Triangle> readAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int create(Triangle triangle) throws TriangleRepositoryException {
        if (triangle == null){
            throw new TriangleRepositoryException("null triangle");
        }
        int currentId = nextId++;
        triangle.setId(currentId);
        storage.put(currentId, triangle);
        return currentId;
    }

    @Override
    public void update(int id, Triangle triangle) throws TriangleRepositoryException {
        if (triangle == null){
            throw new TriangleRepositoryException("null triangle");
        }
        triangle.setId(id);
        storage.replace(id, triangle);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    /**
     * @param specification specification to decide add or no particular triangle in the result
     * @return list of triangles meets the specification
     */
    @Override
    public List<Triangle> getBySpec(Specification specification) {
        List<Triangle> result = new ArrayList<>();
        final Collection<Triangle> values = storage.values();
        if (specification instanceof FindSpecification){
            for (Triangle triangle : values) {
                if (((FindSpecification) specification).isSpecified(triangle)){
                    result.add(triangle);
                }
            }

        }else if(specification instanceof SortSpecification){
            result = new ArrayList<>(values);
            result.sort(((SortSpecification) specification).getComparator());
        }

        return result;
    }
}
