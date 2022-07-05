package main;

import java.awt.*;

public class BackgroundAnimated {

    private Image grafika;
    private int x;
    private int y;

    public BackgroundAnimated(Image grafika, int x, int y) {
        this.grafika = grafika;
        this.x = x;
        this.y = y;
    }
    public Image getGrafika() {
        return grafika;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
}
