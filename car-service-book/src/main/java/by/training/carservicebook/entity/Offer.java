package by.training.carservicebook.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Offer extends Entity{
    private CarRecord carRecord;
    private User master;
    double price;
    private Boolean isSelected;

    public Offer(){};

    public Offer(Integer carRecordId, User master, Double price){
        this.master = master;
        this.carRecord = new CarRecord(carRecordId);
        this.price = price;
        this.isSelected = false;
    }


}
