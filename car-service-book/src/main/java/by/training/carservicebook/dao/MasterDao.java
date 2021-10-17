package by.training.carservicebook.dao;

import by.training.carservicebook.entity.Master;

import java.util.List;

public interface MasterDao extends Dao<Master> {
    void setWorkPrice(Integer carRecordId, double workPrice);//service tender
    List<Integer> doWork();//carRecordId list

}
