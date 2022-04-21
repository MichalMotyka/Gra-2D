package main;

import main.Sound.SoundEffects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    BufferedImage image;
    MapaTestowa mt = new MapaTestowa();
    SoundEffects eSound;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPalyerImage();
        eSound = new SoundEffects();


    }
    //import grafik gracza
    public void getPalyerImage() {
        try {
            stand = ImageIO.read(ClassLoader.getSystemResourceAsStream("proproprotyp.png"));
            particle1 = ImageIO.read(ClassLoader.getSystemResourceAsStream("particle1.png"));
            particle2 = ImageIO.read(ClassLoader.getSystemResourceAsStream("particle2.png"));
            lewo = ImageIO.read(ClassLoader.getSystemResourceAsStream("pixil-frame-0-lewo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //statystyki gracza które ulegaja zmianie w trakcie gry
    public void setDefaultValues() {
        x = 100;
        y = 510;
        playerSpeed = PlayerStats.speed;
        jumpForce = PlayerStats.jumpForce;
        gravity = PlayerStats.gravity;
        jump = false;
        grounded = true;
        ground = 730;
        jumpStart = 0;
    }

    //funkcja odpowiedzialna za poruszanie się oraz inne odziaływania fizyczne na gracza np upadek
    public void update() {
        if (keyHandler.upPressed == true && grounded) {
            jumpStart = y;
            jump = true;
            grounded = false;
            eSound.setFile(0);
            eSound.play();
        }
        if (keyHandler.downPressed == true) {
            //  y +=playerSpeed;
        }
        if (keyHandler.leftPressed == true) {
            // x -=playerSpeed;
            worldMoveX = worldMoveX + playerSpeed;
            image = stand;
        }
        if (keyHandler.rightPressed == true) {
            switch (Config.ActiveMap){
                case "MapaTestowa":
                    mt.drawColider();
                    break;
            }

            if(!solid) {
                worldMoveX = worldMoveX - playerSpeed;
                image = stand;
               // consoleCommand.getX();
            }


        }

        if (jump == false && y <= ground) {
            y += gravity;

            if(y> ground){
                y =ground;
            }

        }
        if (jump == true) {
            y -= gravity;
            if (y <= jumpStart - jumpForce) {
                jump = false;

            }
        }
        if (y >= ground) {
            grounded = true;
            fall = true;
        }


    }
    //funckaj zwraca o ile pxl przemiescil sie swiat
    public int getWorldMove() {
        return worldMoveX;
    }
    //funckja odpowiedzialna zarysowanie gracza w odpowiedniej pozie/animacji
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 90, 90, null);
        }



}
