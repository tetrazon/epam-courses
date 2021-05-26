package by.training.task02branchingstatements.secret.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * secret database emulation
 */
public class DataBase {

    private static Logger logger = LogManager.getLogger(DataBase.class);
    private String dataBaseName;

    public DataBase(String dataBaseName) {
        if (dataBaseName == null || dataBaseName.isEmpty()){
            logger.error("Illegal DB name");
            throw new IllegalArgumentException("DataBase name must be not null and not empty");
        }
        this.dataBaseName = dataBaseName;
    }
}
