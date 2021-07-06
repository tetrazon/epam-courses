package by.training.task05.repository.impl;

import by.training.task05.entity.TriangleProperty;
import by.training.task05.repository.Repository;

import java.util.*;

public class TrianglePropertyRepositoryImpl implements Repository<TriangleProperty> {
    private static int nextId = 1;

    private Map<Integer, TriangleProperty> storage = new HashMap<>();
    private static TrianglePropertyRepositoryImpl repository;

    @Override
    public Optional<TriangleProperty> read(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public int create(TriangleProperty t) {
        int currentId = nextId++;
        storage.put(currentId, t);
        return currentId;
    }

    @Override
    public List<TriangleProperty> readAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(int index, TriangleProperty t) {
        storage.put(index, t);

    }

    @Override
    public void delete(int id) {
        storage.remove(id);

    }
}
