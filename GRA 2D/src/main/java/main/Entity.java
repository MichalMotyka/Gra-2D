package main;

import java.awt.image.BufferedImage;

public class Entity {
    public static int worldMoveX = 0 ;
    public static int x;
    public static int y;
    public static int ground;
    static int playerSpeed;
    int jumpForce;
    int gravity, particlecounter=0,particlenum=0;
    boolean jump;
    static boolean solid=false;
    boolean fall;
    boolean grounded;
    int jumpStart ;
    public BufferedImage stand,lewo,jumoParti,particle1,particle2;
    public String direction;
}
