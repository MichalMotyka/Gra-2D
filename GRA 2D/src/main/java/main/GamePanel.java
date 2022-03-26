package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 25;
    final int maxScreenRow = 20;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    int FPS = 60;
    Color brown = new Color(110, 38, 14);

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drwaInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        double nextDrwaTime = System.nanoTime() + drwaInterval;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drwaInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }


        }
    }

    public void update() {
        player.update();



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        Graphics2D g2 = (Graphics2D) g;
        switch (Config.ActiveMap){
            case "MapaTestowa":
                MapaTestowa.drawBackground(g2);
                g.fillRect(100, 100, 100, 100);
                g.setColor(brown);
                g.fillRect(0, 862, 1280, 100);
                MapaTestowa.draw(g2);

        }

        player.draw(g2);
        g2.dispose();
        g.dispose();
    }

}
