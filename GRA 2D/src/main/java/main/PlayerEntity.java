package main;

import lombok.Data;

@Data
public class PlayerEntity {
    private final String name;
    private final String id;
    private int points;
    public PlayerEntity(String name, String id, int points) {
        this.name = name;
        this.id = id;
        this.points = points;
    }




}
