package by.training.task05.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TriangleProperty {

    private static final double SIDE_DELTA = 0.001;
    private static final double DEG_DELTA = 1.;
    private static final double RIGHT_ANGLE = 90.;

    private TrianglePropertyCalculator calculator = new TrianglePropertyCalculator();

    private double ab;
    private double ac;
    private double bc;

    double angleA;
    double angleB;
    double angleC;

    double area;

    public TriangleProperty(Triangle triangle){
        final Triangle.Point a = triangle.getA();
        final Triangle.Point b = triangle.getB();
        final Triangle.Point c = triangle.getC();

        ab = calculator.calcSide(a, b);
        ac = calculator.calcSide(a, c);
        bc = calculator.calcSide(b, c);

        angleA = calculator.calcAngle(
                a.getX(), b.getX(), c.getX(),
                a.getY(), b.getY(), c.getY(),
                a.getZ(), b.getZ(), c.getZ()
        );

        angleB = calculator.calcAngle(
                b.getX(), c.getX(), a.getX(),
                b.getY(), c.getY(), a.getY(),
                b.getZ(), c.getZ(), a.getZ()
        );

        angleC = calculator.calcAngle(
                c.getX(), b.getX(), a.getX(),
                c.getY(), b.getY(), a.getY(),
                c.getZ(), b.getZ(), a.getZ()
        );

        area = calculator.calcArea(a, b, c);
    }

    public double calcPerimeter(){
        return ab + ac + bc;
    }

    public boolean isRectangular() {
        return Math.abs(angleA - RIGHT_ANGLE) < DEG_DELTA ||
                Math.abs(angleB - RIGHT_ANGLE) < DEG_DELTA ||
                Math.abs(angleC - RIGHT_ANGLE) < DEG_DELTA;
    }

    public boolean isAcute() {
        return angleA < RIGHT_ANGLE &&
                angleB < RIGHT_ANGLE &&
                angleC < RIGHT_ANGLE;
    }

    public boolean isObtuse() {
        return angleA > RIGHT_ANGLE ||
                angleB > RIGHT_ANGLE ||
                angleC > RIGHT_ANGLE;
    }

    public boolean isIsosceles() {
        return Math.abs(ab - ac) < SIDE_DELTA ||
                Math.abs(ab - bc) < SIDE_DELTA;
    }

    public boolean isEquilateral() {
        return Math.abs(ab - ac) < SIDE_DELTA &&
                Math.abs(ab - bc) < SIDE_DELTA;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TriangleProperty{");
        sb.append("ab=").append(ab);
        sb.append(", ac=").append(ac);
        sb.append(", bc=").append(bc);
        sb.append(", angleA=").append(angleA);
        sb.append(", angleB=").append(angleB);
        sb.append(", angleC=").append(angleC);
        sb.append(", area=").append(area);
        sb.append(", perimeter=").append(calcPerimeter());
        sb.append(", isRectangular: ").append(isRectangular());
        sb.append(", isAcute: ").append(isAcute());
        sb.append(", isObtuse: ").append(isObtuse());
        sb.append(", isIsosceles: ").append(isIsosceles());
        sb.append(", isEquilateral: ").append(isEquilateral());
        sb.append('}');
        return sb.toString();
    }
}
