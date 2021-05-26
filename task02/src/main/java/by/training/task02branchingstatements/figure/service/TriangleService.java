package by.training.task02branchingstatements.figure.service;

import by.training.task02branchingstatements.figure.model.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class for triangle properties evaluation
 */
public class TriangleService {
    private static Logger logger = LogManager.getLogger(TriangleService.class);

    /**
     *returns true if the triangle is equilateral
     */
    public boolean isEquilateral(Triangle triangle){
        logger.info("isEquilateral() is called");
        return triangle.getA() == triangle.getB()
                && triangle.getA() == triangle.getC();
    }
}
