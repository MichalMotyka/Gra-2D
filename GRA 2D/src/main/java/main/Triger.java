package main;

import lombok.Data;

import java.awt.*;

@Data
public class Triger {
    private int x;
    private int y;
    private int width;
    private int height;

    private final Type TrigerType;
    enum Type{
        COIN,
        DEATH
    }
    public Triger(int x, int y,int width,int height,Type TrigerType) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.TrigerType = TrigerType;
    }
}
