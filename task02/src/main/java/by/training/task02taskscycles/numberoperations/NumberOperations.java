package by.training.task02taskscycles.numberoperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class representing tasks with cycles
 */
public final class NumberOperations {

    private static Logger logger = LogManager.getLogger(NumberOperations.class);

    /**
     * print numbers in log
     * @param from start inclusive index
     * @param to end inclusive index
     */
    public static void printNumbersInLog(int from, int to){
        checkIndex(from, to);
        for (int i = from; i <= to; i++) {
            logger.info(i);
        }
    }

    private static void checkIndex(int from, int to) {
        if (to < from){
            logger.error("checkIndex fail");
            throw new IllegalArgumentException("\"from\" index must be greater than \"to\" index");
        }
    }

    /**
     *
     * @param from start inclusive index
     * @param to end inclusive index
     * @return sum of squares starting from "from" to "to" index
     */
    public static int getSumSquare(int from, int to){
        checkIndex(from, to);
        int result = 0;
        for (int i = from; i <=to ; i++) {
            result += i*i;
        }
        return result;
    }

    /**
     *
     * @param a double value
     * @param n non negative int value
     @return formula a(a+1)...(a+n-1) result
     */
    public static double getFormulaResult(double a, int n){
        if (n <= 0){
            logger.error("illegal argument in getFormulaResult()");
            throw new IllegalArgumentException("n must be non negative");
        }
        double result = a;
        for (int i = 0; i < n; i++) {
            result *= (a + i);
        }
        return result;
    }

    /**
     * @return factorial of number
     */
    public static int getFactorial(int number){
        if (number < 0){
            logger.error("illegal argument in getFactorial()");
            throw new IllegalArgumentException("number must be non negative");
        }
        if (number == 0 || number == 1){
            return 1;
        }

        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    /**
     *
     * @param number int value
     * @return max digit of the number
     */
    public static int getMaxDigitFromNumber(int number){
        number = Math.abs(number);
        int maxDigit = 0;
        int tmpDigit;
        do {
            tmpDigit = number % 10;
            if (tmpDigit > maxDigit){
                maxDigit = tmpDigit;
            }
            number /= 10;
        } while (number != 0);
        return maxDigit;
    }
}
