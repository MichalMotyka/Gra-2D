package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GamePanel extends JPanel implements Runnable {
    //Zmienne odpowiedajace za rozdzielczość oraz maksymalna liczbe fps
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 25;
    final int maxScreenRow = 20;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    int FPS = 60;
    long t = System.currentTimeMillis();
    long end = t+1000;
    Color brown = new Color(110, 38, 14);
    int fpscounter = 0 ;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    MapaTestowa mt = new MapaTestowa();
    MapaPoziom1 mapaPoziom1 = new MapaPoziom1();
    endlessMode endlessMode = new endlessMode();
    static boolean clicked = false;
    boolean canChangePanel = false;
    Thread gameThread;
    public int licznik = 0;

    //funkcja odpowiadająca za utworzenie pustego podstawego panelu, w tym momęcie podpinane sa kontrolery klawiszy
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    //rozpoczyna gre
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread() {
        gameThread.stop();
    }
    void commands(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        new ConsoleCommand(command);
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
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
                drawCount++;
            }
        }

    }
    //funckaja wykonujaca się raz na 1/60 sekundy
    public void update() throws IOException {
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
        switch (Config.ActiveMap){
            case "MapaTestowa":
                mt.drawBackground(g2);
                  g.fillRect(100, 100, 100, 100);
                  g.setColor(brown);
                  g.fillRect(0, 862, screenWidth, 100);
                try {
                    mt.draw(g2);
                    mt.drawColider();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Endles":
                try {
                    endlessMode.drawBackground(g2);
                    endlessMode.draw(g2);
                    endlessMode.drawColider();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case "Poziom1":
                try {
                    mapaPoziom1.drawBackground(g2);
                    mapaPoziom1.draw(g2);
                    mapaPoziom1.drawColider();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        player.drawParticle(g2);
        player.draw(g2);
        if(keyHandler.stopPressed){
//            this.add(new JLabel("PAUZA"));
            g2.setColor(new Color(255, 255, 255, 80));
            g2.fillRect(0,0,screenWidth,screenHeight);
            Image img = null;
            try {
                img = ImageIO.read(new File("src/main/resources/pauza.png"));
            } catch (IOException e) {
            }
            g2.drawImage(img, (screenWidth - img.getWidth(null)) / 2, 150, null);
        }

        if(TrigerController.death) {
            g2.setColor(new Color(255, 255, 255, 80));
            g2.fillRect(0,0,screenWidth,screenHeight);
            Image img = null;
            Image img2 = null;
            try {
                img = ImageIO.read(new File("src/main/resources/lose.png"));
                img2 = ImageIO.read(new File("src/main/resources/menubtn.png"));
            } catch (IOException e) {
            }
            g2.drawImage(img, (screenWidth - img.getWidth(null)) / 2, 150, null);
            g2.drawImage(img2, (screenWidth - 200) / 2, 450, 200, 100,null);

            if(!clicked) {
                clicked = true;
                MouseHandler.pressMouse(this);
                canChangePanel = true;
            }
        }


        g2.dispose();
        g.dispose();
        Points points = new Points();
        points.updatepoints();
    }
    void addListenerToMenuButton(JFrame frame, UserMenu userMenu, GamePanel gamePanel) {
        System.out.println(canChangePanel);
        if(canChangePanel) {
            frame.remove(gamePanel);
            frame.add(userMenu);
            frame.repaint();
            frame.revalidate();
            System.gc();
        }
    }

}
