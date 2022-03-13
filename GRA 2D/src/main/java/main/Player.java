package main;


import main.GamePanel;
import  main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    BufferedImage image;

    public Player(GamePanel gp,KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPalyerImage();


    }
    public void getPalyerImage(){
        try {
            stand = ImageIO.read(ClassLoader.getSystemResourceAsStream("pixil-frame-0.png"));
            lewo =ImageIO.read(ClassLoader.getSystemResourceAsStream("pixil-frame-0-lewo.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void  setDefaultValues(){
        x = 100;
        y = 500;
         playerSpeed=PlayerStats.speed;
         jumpForce = PlayerStats.jumpForce;
         gravity = PlayerStats.gravity;
         jump = false;
         grounded = true;
         jumpStart = 0;
    }
    public void update(){
        if(keyHandler.upPressed == true && grounded){
            jumpStart = y;
            jump = true;
            grounded = false;
        }
        if(keyHandler.downPressed == true){
          //  y +=playerSpeed;
        }
        if(keyHandler.leftPressed == true){
            x -=playerSpeed;
            image= lewo;
        }
        if(keyHandler.rightPressed == true){
            x +=playerSpeed;
            image = stand;
        }
        if(jump==false && y <= 760 ){
            y += gravity;
        }
        if(jump==true){
            y -=gravity;
            if(y == jumpStart-jumpForce){
                jump = false;
            }
        }
        if(y >= 760){
            grounded =true;
        }

    }
    public void draw(Graphics2D g2){
        g2.drawImage(image,x,y,90, 90,null);

    }


}
