package main;

public class Particle {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private int speed;
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}




    public Particle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
