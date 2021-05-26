package by.training.task02branchingstatements.firesensor.service;

import by.training.task02branchingstatements.firesensor.model.FireSensor;

/**
 * class for fire sensor work emulation
 */
public class FireSensorService {

    /**
     * simulates the alarm triggering alarm reaching temperature above 60 degree
     * by increasing the temperature by 10 degree
     */
    public void emulateFireSensorTrigger(FireSensor fireSensor){
        while (true){
            fireSensor.heatUpByTenDegree();
        }
    }
}
