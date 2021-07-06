package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.SortSpecification;

import java.util.Comparator;

public class SortByZSpecification implements SortSpecification {
    @Override
    public Comparator<Triangle> getComparator() {
        final Comparator<Triangle> aXComparator = (a, b) -> (int) (a.getA().getZ() - b.getA().getZ());
        final Comparator<Triangle> bXComparator = (a, b) -> (int) (a.getB().getZ() - b.getB().getZ());
        final Comparator<Triangle> cXComparator = (a, b) -> (int) (a.getC().getZ() - b.getC().getZ());
        return aXComparator.thenComparing(bXComparator).thenComparing(cXComparator);
    }
}
