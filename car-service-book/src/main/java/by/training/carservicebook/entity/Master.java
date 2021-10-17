package by.training.carservicebook.entity;

import lombok.Data;

import java.util.List;

@Data
public class Master extends Entity {
    //private Integer id;
    private User user;
    private String category;
}
