package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.SortSpecification;

import java.util.Comparator;

public class SortByXSpecification implements SortSpecification {
    @Override
    public Comparator<Triangle> getComparator() {
        final Comparator<Triangle> aXComparator = (a, b) -> (int) (a.getA().getX() - b.getA().getX());
        final Comparator<Triangle> bXComparator = (a, b) -> (int) (a.getB().getX() - b.getB().getX());
        final Comparator<Triangle> cXComparator = (a, b) -> (int) (a.getC().getX() - b.getC().getX());
        return aXComparator.thenComparing(bXComparator).thenComparing(cXComparator);
    }
}
