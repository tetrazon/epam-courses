package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TriangleProperty;
import by.training.task05.repository.Repository;
import by.training.task05.specification.FindSpecification;
import by.training.task05.specification.Specification;
import by.training.task05.specification.exception.SpecificationException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AreaFindSpecification implements FindSpecification {
    private double minArea;
    private double maxArea;
    private Repository<TriangleProperty> repository;
    @Override
    public boolean isSpecified(Triangle triangle) {
        final Optional<TriangleProperty> trianglePropOpt = repository.read(triangle.getId());
        if (trianglePropOpt.isPresent()){
            final double area = trianglePropOpt.get().getArea();
            return area < maxArea && area > minArea;
        } else {
            throw  new SpecificationException("no such property");
        }
    }
}
