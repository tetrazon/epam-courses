package by.training.task01linearalgorithm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class consists exclusively of static linear algorithm methods
 * that operate on or return double values
 *
 */
public final class LinearPrograms {
    private static Logger logger = LogManager.getLogger(LinearPrograms.class);

    // Suppresses default constructor, ensuring non-instanceability.
    private LinearPrograms(){}

    /**
     * returns a sum: a + b
     */
    public static double getSum(double a, double b){
        logger.info(String.format("getSum() is invoked, a = %s; b = %s", a, b));
        return a + b;
    }

    /**
     * returns a difference: minuend - subtrahend
     */
    public static double getDifference(double minuend, double subtrahend){
        return minuend - subtrahend;
    }

    /**
     * returns a multiply: a * b
     */
    public static double getMultiply(double a, double b){
        return a * b;
    }


    /**
     * returns the result of division: dividend / divider
     * @param divider is not zero double value
     * @throws IllegalArgumentException if the divider equals zero
     */
    public static double getDivision(double dividend, double divider){
        logger.info(String.format("getDivision() is called, divident = %s, divider = %s", dividend, divider));
        if (divider == 0.){
            logger.error("divide by zero");
            throw new IllegalArgumentException("you cannot divide by zero");
        }
        return dividend / divider;
    }

    /**
     * calculates the result of (a * b) / (c * d) - (a * b - c) / (c * d) expression
     * @param c is not zero double value
     * @param d is not zero double value
     * @throws IllegalArgumentException if the divider equals zero (c or d)
     */
    public static double getFormula9Result(double a, double b, double c, double d) {
        logger.info("getFormula9Result() is called");
        if (c == 0. || d == 0.){
            logger.error("divide by zero");
            throw new IllegalArgumentException("you cannot divide by zero");
        }
        return (a * b) / (c * d) - (a * b - c) / (c * d);
    }

    /**
     * returns a average value of a sum of a^3 + b^3
     */
    public static double getCubeAverage(double a, double b){
        logger.info(String.format("getCubeAverage() is called, a = %s, b = %s", a, b));
        return (a * a * a + b * b * b) / 2;
    }

    /**
     * returns the geometric mean of the absolute value
     * of the (a * b) expression
     */
    public static double getAbsGeometricMean(double a, double b){
        logger.info(String.format("getAbsGeometricMean() is called, a = %s, b = %s", a, b));
        return Math.sqrt(Math.abs(a * b));
    }

    /**
     * returns the solve represented by the double array
     * of the expression: ах^2 + bх + с = 0
     * it's assumed a != 0 and (b * b - 4.0 * a * c) > 0
     * @param a - not zero coefficient
     */
    public static double[] getQuadraticEquationResults(double a, double b, double c) {
        if(a == 0.){
            logger.error(String.format("getQuadraticEquationResults() called with a = %s", a));
            throw new IllegalArgumentException("the method requires a != 0");
        }
        double [] result = new double[2];
        double discriminant = (b * b - 4.0 * a * c);
        if (discriminant <= 0){
            logger.error("getQuadraticEquationResults() has non positive discriminant");
            throw new IllegalArgumentException("non positive discriminant!");
        }
        result[0] = (-b + Math.sqrt(discriminant))/(a * 2.0);
        result[1] = (-b - Math.sqrt(discriminant))/(a * 2.0);
        return result;
    }

}

