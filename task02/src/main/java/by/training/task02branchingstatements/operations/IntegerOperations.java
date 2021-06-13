package by.training.task02branchingstatements.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provide branching methods for int numbers
 */
public final class IntegerOperations {

    private static Logger logger = LogManager.getLogger(IntegerOperations.class);

    //insures non-instansebility
    private IntegerOperations(){}

    /**
     * Compares two ints
     * @param a first
     * @param b second
     * @return 7 if a < b otherwise 8
     */
    public static int compareTwoInts(int a, int b){
        logger.info("compareTwoInts() is called");
        return a < b ? 7 : 8;
    }

    /**
     *changes the array size of 2 containing the bigger value of ab[] array
     * or returns {0,0} if ab[0] == ab[1]
     */
    public static void setAllBiggerOrZero(int[]ab){
        logger.info("setAllBiggerOrZero() is called");
        if (ab.length != 2){
            logger.error("illegal argument in setAllBiggerOrZero()");
            throw new IllegalArgumentException("the size of array must be 2");
        }

        if (ab[1] > ab[0]){

            ab[0] = ab[1];

        } else if (ab[0] > ab[1]){

            ab[1] = ab[0];

        } else {

            ab[0] = ab[1] = 0;

        }

    }


}
