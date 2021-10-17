package by.training.carservicebook.dao;

import by.training.carservicebook.entity.Entity;
import lombok.Data;

import java.sql.Connection;

@Data
public abstract class BaseDao <T extends Entity> {
    protected Connection connection;
}
