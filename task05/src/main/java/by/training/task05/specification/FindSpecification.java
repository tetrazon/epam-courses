package by.training.task05.specification;

import by.training.task05.entity.Triangle;

public interface FindSpecification extends Specification {
    boolean isSpecified(Triangle triangle);
}
