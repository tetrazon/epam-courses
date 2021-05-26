package by.training.task02branchingstatements.firesensor.model;

import by.training.task02branchingstatements.firesensor.exception.FireSensorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Emulates the fire sensor work that triggers at 60 Celsius degree
 */
public class FireSensor {

    private static Logger logger = LogManager.getLogger(FireSensor.class);
    private static int UPPER_THRESHOLD = 60;

    private int currentTemperature;

    public FireSensor(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    /**
     * increases the currentTemperature by 10 degree
     */
    public void heatUpByTenDegree(){
        logger.info(String.format("increase the temperature %d by 10 degree", currentTemperature));
        currentTemperature += 10;
        if (currentTemperature > 60){
            logger.error("temperature in the room exceeded 60 Celsius degree");
            throw new FireSensorException("temperature in the room exceeded 60 Celsius degree");
        }
    }




}
