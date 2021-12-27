package by.training.carservicebook.entity;

import lombok.Data;

import java.util.List;

@Data
public class User extends Entity{
    private String login;
    private String password;
    private Role role;
    private String email;
    private String mobilePhone;
    private String name;
    private String surname;
    private Boolean isBanned;
    private Boolean isArchived;
    private String district;
    private List<Car> cars;
    private String category;

    public User(Integer userId) {
        super(userId);
    }

    public User() {
        super(null);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(super.getId()).append('\'');
        sb.append("login='").append(login).append('\'');
        sb.append(", role=").append(role);
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", isBanned=").append(isBanned);
        sb.append(", isArchived=").append(isArchived);
        sb.append(", district='").append(district).append('\'');
        sb.append(", cars=").append(cars);
        sb.append('}');
        return sb.toString();
    }

}
