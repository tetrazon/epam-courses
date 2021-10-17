package by.training.carservicebook.entity;

import lombok.Data;

import java.util.List;

@Data
public class User extends Entity{
    //private Integer id;
    private String login;
    private String password;
    private Role role;
    private String email;
    private String mobilePhone;
    private String name;
    private String surname;
    private boolean isBanned;
    private boolean isArchived;
    private String district;
    private List<Car> cars;

}
