package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.SortSpecification;

import java.util.Comparator;

public class SortByYSpecification implements SortSpecification {
    @Override
    public Comparator<Triangle> getComparator() {
        final Comparator<Triangle> aXComparator = (a, b) -> (int) (a.getA().getY() - b.getA().getY());
        final Comparator<Triangle> bXComparator = (a, b) -> (int) (a.getB().getY() - b.getB().getY());
        final Comparator<Triangle> cXComparator = (a, b) -> (int) (a.getC().getY() - b.getC().getY());
        return aXComparator.thenComparing(bXComparator).thenComparing(cXComparator);
    }
}
