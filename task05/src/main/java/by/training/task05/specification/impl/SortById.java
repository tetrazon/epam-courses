package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.SortSpecification;

import java.util.Comparator;

public class SortById implements SortSpecification {
    @Override
    public Comparator<Triangle> getComparator() {
        return Comparator.comparing(Triangle::getId);
    }
}
