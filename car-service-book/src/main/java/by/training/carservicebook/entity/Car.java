package by.training.carservicebook.entity;

import lombok.Data;

import java.util.List;
@Data
public class Car extends Entity {
    private User user;
    private String model;
    private double mileage;
    private int year;
    private List<CarRecord> carRecords;
    private List<CarRecord> carHistory;

    public Car(Integer id) {
        super(id);
    }

    public Car() {
    }
}
