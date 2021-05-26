package by.training.task02branchingstatements.secret.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class AccessLevel represents the access level for particular databases
 * through particular passwords
 */
public class AccessLevel {

    private static Logger logger = LogManager.getLogger(AccessLevel.class);

    private String accessLevelName;
    private List<DataBase> dataBases;
    private List<String> passwords;

    public AccessLevel(String accessLevelName, List<DataBase> dataBases, List<String> passwords) {
        if (accessLevelName == null ||
                dataBases == null ||
                passwords == null ||
                accessLevelName.isEmpty() ||
                dataBases.isEmpty() ||
                passwords.isEmpty()
        ){
            logger.error("Wrong argument in the constructor parameters!");
           throw new IllegalArgumentException("Wrong argument in the constructor parameters!");
        }
        this.accessLevelName = accessLevelName;
        this.dataBases = dataBases;
        this.passwords = passwords;
    }

    public String getAccessLevelName() {
        return accessLevelName;
    }

    public List<DataBase> getDataBases() {
        return dataBases;
    }

    public List<String> getPasswords() {
        return passwords;
    }
}
