package main;

import main.Sound.SoundEffects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    BufferedImage image;
    MapaTestowa mt = new MapaTestowa();
    JumpCooldown jumpCooldown = new JumpCooldown();
    long currentTimeAfterGrounded;
    boolean canJumpAfterGrounded = true;
    SoundEffects eSound;
    public static Skins skise;
    public static Integer ChoosedSkin;


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
            stand = GetChoosedSkin();
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
    public static BufferedImage GetChoosedSkin() throws IOException {
        skise = new Skins();
        if(ChoosedSkin == null) {
            return ImageIO.read(ClassLoader.getSystemResourceAsStream("skins/skullhead1.png"));
        }
        else {
            return ImageIO.read(new File("src/main/resources/skins", skise.skins.get(ChoosedSkin)));
        }
    }

    //funkcja odpowiedzialna za poruszanie się oraz inne odziaływania fizyczne na gracza np upadek
    public void update() {
        if (keyHandler.upPressed == true && grounded && jumpCooldown.checkIfCanJump() && canJumpAfterGrounded) {
            jumpCooldown.setCanJump();
            // @TODO: dać w inne miejsce ewentualnie,teraz po wykonaniu skoku odlicza czas, a powinno po opadnięciu i wtey odliczac np 200 ms.
            currentTimeAfterGrounded = System.currentTimeMillis() + 1650;
            canJumpAfterGrounded = false;
            jumpStart = y;
            jump = true;
            grounded = false;
            eSound.setFile(0);
            eSound.play();
        }
        if (keyHandler.downPressed == true) {
            //  y +=playerSpeed;
        }
//        if (keyHandler.leftPressed == true) {
//            // x -=playerSpeed;
//            worldMoveX = worldMoveX + playerSpeed;
//            image = stand;
//        }
        if (true) {
            jumpCooldown.checkIfCanJump();
            if(!solid) {
                worldMoveX = worldMoveX - playerSpeed;
                image = stand;
            }
        }
        // if(keyHandler.menuPressed == true){
        //     popupmenu = true;
        // }

        if (jump == false && y < ground) {
            y += gravity;
            if(y>= ground){
                Particles.addNewParticle(x+50,ground+90);
                y =ground;
            }

        }
        if (jump == true) {
            if (!ceilingsolid){
            y -= gravity;
            if (y <= jumpStart - jumpForce) {
                jump = false;
              }
            }
            else
                jump=false;
            }

        if (y >= ground) {
            grounded = true;
            if(currentTimeAfterGrounded <= System.currentTimeMillis() && y >= ground) {
                canJumpAfterGrounded = true;
            }
            fall = true;
        }

    }
    //funckaj zwraca o ile pxl przemiescil sie swiat
    public int getWorldMove() {
        return worldMoveX;
    }
    public void drawParticle(Graphics2D g2){
        Particles.DrawParticle(g2);
        Particles.drawRunParticle(g2);
    }
    //funckja odpowiedzialna zarysowanie gracza w odpowiedniej pozie/animacji
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 90, 90, null);
        }



}
