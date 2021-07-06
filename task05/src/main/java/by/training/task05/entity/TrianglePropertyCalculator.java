package by.training.task05.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrianglePropertyCalculator {
    private static final double PI_IN_DEGREE = 180.0;

    public double calcSide(Triangle.Point start, Triangle.Point finish){
        double result = Math.sqrt(
                Math.pow(finish.getX() - start.getX(), 2)
                        + Math.pow(finish.getY() - start.getY(), 2)
                        + Math.pow(finish.getZ() - start.getZ(), 2));
        return round(result, 10);
    }

    public double calcAngle(double x1, double x2, double x3,
                             double y1, double y2, double y3,
                             double z1, double z2, double z3)
    {

        double num = (x2-x1)*(x3-x1)+(y2-y1)*(y3-y1)+(z2-z1)*(z3-z1);

        double den = Math.sqrt(Math.pow((x2-x1),2)+
                Math.pow((y2-y1),2)+Math.pow((z2-z1),2))*
                Math.sqrt(Math.pow((x3-x1),2)+
                        Math.pow((y3-y1),2)+Math.pow((z3-z1),2));

        if (round(den, 2) == 0.){
            throw new IllegalArgumentException("WrongTriangle");
        }

        double result = Math.acos(num / den) * (PI_IN_DEGREE / Math.PI);
        return round(result, 0);
    }

    private double round(double val, int scale){
        return BigDecimal.valueOf(val).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public double calcArea(Triangle.Point a, Triangle.Point b, Triangle.Point c){
        final double ab = calcSide(a, b);
        final double cb = calcSide(c, b);
        final double ac = calcSide(a, c);
        final double halfPerimeter = 0.5 * (ab + cb + ac);
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - ab) * (halfPerimeter - cb) * (halfPerimeter -ac));
        area = round(area, 2);
        return area;
    }
}
