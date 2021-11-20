package by.training.carservicebook.entity;

import lombok.Data;

@Data
public abstract class Entity {
    private Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Entity() {
    }
}
