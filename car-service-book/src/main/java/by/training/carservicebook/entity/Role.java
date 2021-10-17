package by.training.carservicebook.entity;

public enum Role {
    ADMIN("администратор"),
    CLIENT("клиент"),
    MASTER("мастер");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
