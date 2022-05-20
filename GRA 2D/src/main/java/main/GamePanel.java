package main;

import main.Sound.SoundEffects;
import main.Sound.SoundMaps;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    //Zmienne odpowiedajace za rozdzielczość oraz maksymalna liczbe fps
    SoundEffects eSound;
    SoundMaps mSound;
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 25;
    long t = System.currentTimeMillis();
    long end = t+1000;
    int fpscounter = 0 ;
    final int maxScreenRow = 20;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    int FPS = 60;
    Color brown = new Color(110, 38, 14);

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    MapaPoziom1 mk = new MapaPoziom1();
    Thread gameThread;

    //funkcja odpowiadająca za utworzenie pustego podstawego panelu, w tym momęcie podpinane sa kontrolery klawiszy
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setLayout(new GridLayout(2, 1));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    //rozpoczyna gre
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        eSound = new SoundEffects();
        mSound = new SoundMaps();
        mSound.setFile(0);
        mSound.play();
    }

    //funkcja odpowiedzialna za utrzymanie klatkaż w odpowiedniej wartosci
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
    //funckaja wykonujaca się raz na 1/60 sekundy
    public void update() {
        player.update();
    }
    //funckja odpowiedzialna za rysowanie komponentów na ekranie
    public void paintComponent(Graphics g) {
        if (System.currentTimeMillis() <= end){
            fpscounter ++;
        }else{
            System.out.println("FPS: "+fpscounter);
            fpscounter=0;
            end = System.currentTimeMillis()+1000;
        }
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        Graphics2D g2 = (Graphics2D) g;
        System.out.println(Config.ActiveMap);
        switch (Config.ActiveMap){
            case "MapaPoziom1":
                mk.drawBackground(g2);
                g.fillRect(100, 100, 100, 100);
                g.setColor(brown);
                g.fillRect(0, 862, screenWidth, 100);
                try {
                    mk.draw(g2);
                    mk.drawColider();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
////            case "Endles":
//                try {
////                    endlessMode.drawBackground(g2);
////                    endlessMode.draw(g2);
////                    endlessMode.drawColider();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
        }
//        player.drawParticle(g2);
        player.draw(g2);

        g2.dispose();
        Points points=new Points();
        points.updatepoints();
        g.dispose();
    }

}
