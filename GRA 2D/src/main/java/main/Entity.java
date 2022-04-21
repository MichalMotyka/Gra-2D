package main;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    int playerSpeed;
    int jumpForce;
    int gravity ;
    boolean jump;
    boolean grounded;
    int jumpStart ;
    public BufferedImage stand,lewo;
    public String direction;
}
