package by.training.carservicebook.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class CarRecord extends Entity {
    private Car car;
    private String category;
    private Date recordDate;
    private int monthsInterval;
    private int kmInterval;
    private Boolean isPeriodic;
    private String description;
    private Master master;
    double workPrice;
    private Boolean isTender;
}
