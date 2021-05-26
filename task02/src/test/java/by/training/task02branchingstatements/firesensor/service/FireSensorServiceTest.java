package by.training.task02branchingstatements.firesensor.service;

import by.training.task02branchingstatements.firesensor.exception.FireSensorException;
import by.training.task02branchingstatements.firesensor.model.FireSensor;
import org.testng.annotations.Test;

public class FireSensorServiceTest {

    @Test(description = "starts triggerAlarm() method",
            expectedExceptions = FireSensorException.class,
            expectedExceptionsMessageRegExp = "temperature in the room exceeded 60 Celsius degree")
    public void testEmulateFireSensorTrigger() {
        FireSensor fireSensor = new FireSensor(0);
        FireSensorService fireSensorService = new FireSensorService();
        fireSensorService.emulateFireSensorTrigger(fireSensor);
    }
}