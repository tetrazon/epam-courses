package by.training.task05.validator;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TrianglePropertyCalculator;

public class TriangleValidator {
    private static final double DELTA = 0.001;
    private static final TriangleValidator instance = new TriangleValidator();

    private TrianglePropertyCalculator calculator = new TrianglePropertyCalculator();

    private TriangleValidator(){}

    public static TriangleValidator getInstance() {
        return instance;
    }

    public boolean isTriangle(final Triangle triangle){
        final Triangle.Point a = triangle.getA();
        final Triangle.Point b = triangle.getB();
        final Triangle.Point c = triangle.getC();
        return calculator.calcArea(a, b, c) > DELTA;
    }
}
