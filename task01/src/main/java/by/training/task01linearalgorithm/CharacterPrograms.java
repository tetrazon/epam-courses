package by.training.task01linearalgorithm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class consists exclusively of static linear algorithm methods
 * that operate on char and returns chars
 * or indexes of particular char in the int representation
 */
public class CharacterPrograms {
    private static Logger logger = LogManager.getLogger(CharacterPrograms.class);

    // Suppresses default constructor, ensuring non-instanceability.
    private CharacterPrograms(){};

    /**
     * returns the serial number representation of a character
     */
    public static int getCharSerialNumber(char ch){
        logger.info(String.format("getCharSerialNumber() is called, ch = %s", ch));
        return ch;
    }

    /**
     *returns the previous character relatively ch
     */
    public static char getPreviousCharacter(char ch){
        logger.info(String.format("getPreviousCharacter() is called, ch = %s", ch));
        return (char) (ch - 1);
    }

    /**
     *returns the next character following by ch
     */
    public static char getNextCharacter(char ch){
        logger.info(String.format("getNextCharacter() is called, ch = %s", ch));
        return (char) (ch + 1);
    }


}
