package by.training.task02branchingstatements.figure.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class Triangle with int a, b, c sides.
 *
 */
public class Triangle {

    private static Logger logger = LogManager.getLogger(Triangle.class);

    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0){
            logger.error("wrong argument in the constructor Triangle()");
            throw new IllegalArgumentException("The triangle sides must be positive");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}
