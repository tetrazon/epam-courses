package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.SortSpecification;

import java.util.Comparator;

public class SortByXYZSpecification implements SortSpecification {
    @Override
    public Comparator<Triangle> getComparator() {
        return new SortByXSpecification().getComparator()
                .thenComparing(new SortByYSpecification().getComparator())
                .thenComparing(new SortByZSpecification().getComparator());
    }
}
