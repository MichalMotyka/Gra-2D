package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements  Runnable{
        final int originalTitleSize = 16;
        final int scale = 3;
        public final int titleSize = originalTitleSize * scale;
        final int maxScreenCol = 25;
        final int maxScreenRow = 20;
        final int screenWidth = titleSize * maxScreenCol;
        final int screenHeight = titleSize * maxScreenRow;
        int FPS = 60;
        Color brown = new Color(110, 38, 14);
        Image backgroundImage = ImageIO.read(new File("src/main/resources/tlo.png"));
        Image trawa = ImageIO.read(new File("src/main/resources/trawa.png"));
        Image zemia = ImageIO.read(new File("src/main/resources/ziemia.png"));
        Image chmura = ImageIO.read(new File("src/main/resources/chmury.png"));


        KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this,keyHandler);
        Thread gameThread;

        int playerX =100;
        int playerY =100;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run(){

        double drwaInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime =System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        double nextDrwaTime = System.nanoTime() + drwaInterval;
        while (gameThread !=null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drwaInterval;
            timer +=(currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer = 0;
            }



        }
    }
    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        g.setColor(Color.ORANGE);
        g.fillRect(100,100,100,100);
        for(int x = 0; x < 3000 ; x += 40){

            g.drawImage(zemia, x, 820, this);
            g.drawImage(trawa,x,800,50,50,this);

        }
        g.setColor(brown);
        g.fillRect(0,862,2000,100);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }

}
