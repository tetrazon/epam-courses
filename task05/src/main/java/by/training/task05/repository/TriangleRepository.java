package by.training.task05.repository;

import by.training.task05.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface TriangleRepository<T> extends Repository<T> {
    List<T> getBySpec(Specification specification);
}
