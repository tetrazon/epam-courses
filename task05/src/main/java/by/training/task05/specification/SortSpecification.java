package by.training.task05.specification;

import by.training.task05.entity.Triangle;

import java.util.Comparator;

public interface SortSpecification extends Specification {
    Comparator<Triangle> getComparator();

}


