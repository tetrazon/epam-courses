package by.training.carservicebook.entity;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString(callSuper=true)
public class CarRecord extends Entity {
    private Car car;
    private String category;
    private Date recordDate;
    private int monthsInterval;
    private int kmInterval;
    private Boolean isPeriodic;
    private String description;
    private User master;
    double workPrice;
    private Boolean isTender;

    public CarRecord (){}
    public CarRecord (Integer carRecordId) {
        super(carRecordId);
    }


}
