package by.training.task05.specification.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.specification.FindSpecification;

public class FirstOctantFindSpecification implements FindSpecification {
    @Override
    public boolean isSpecified(Triangle triangle) {
        return triangle.getA().getX() >= 0. &&
                triangle.getA().getY() >= 0. &&
                triangle.getA().getZ() >= 0. &&
                triangle.getB().getX() >= 0. &&
                triangle.getB().getY() >= 0. &&
                triangle.getB().getZ() >= 0. &&
                triangle.getC().getX() >= 0. &&
                triangle.getC().getY() >= 0. &&
                triangle.getC().getZ() >= 0.;
    }
}
