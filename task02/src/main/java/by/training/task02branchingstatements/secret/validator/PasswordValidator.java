package by.training.task02branchingstatements.secret.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * password validator
 * check the string for correctness(4 digits)
 */
public final class PasswordValidator {

    private static Logger logger = LogManager.getLogger(PasswordValidator.class);

    //regex for password: only 4 digits
    private static Pattern PASSWORD_PATTERN = Pattern.compile("\\d{4}");

    private PasswordValidator(){}

    public static boolean isCorrectPassword(String passwordToCheck) {

        if (passwordToCheck == null) {
            return false;
        }

        logger.info(String.format("password to check: %s", passwordToCheck));

        return PASSWORD_PATTERN.matcher(passwordToCheck).matches();
    }

}
