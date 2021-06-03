package by.training.task01linearalgorithm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class consists exclusively of static linear algorithm methods
 * that operate on char and returns chars
 * or indexes of particular char in the int representation
 */
public final class CharacterPrograms {
    private static Logger logger = LogManager.getLogger(CharacterPrograms.class);

    // Suppresses default constructor, ensuring non-instanceability.
    private CharacterPrograms(){};

    /**
     * returns the serial number representation of a character
     */
    public static int calcCharSerialNumber(char ch){
        logger.info(String.format("calcCharSerialNumber() is called, ch = %s", ch));
        return ch;
    }

    /**
     *returns the previous character relatively ch
     */
    public static char calcPreviousCharacter(char ch){
        logger.info(String.format("calcPreviousCharacter() is called, ch = %s", ch));
        return (char) (ch - 1);
    }

    /**
     *returns the next character following by ch
     */
    public static char calcNextCharacter(char ch){
        logger.info(String.format("calcNextCharacter() is called, ch = %s", ch));
        return (char) (ch + 1);
    }


}
