package by.training.carservicebook.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class CarRecord extends Entity {
    //private Integer id;
    private Car car;
    private String category;
    private Date recordDate;
    private int monthInterval;
    private long kmInterval;
    boolean isPeriodicOperation;
    private String description;
    private Master master;
    double workPrice;
    boolean isOnTender;
}
