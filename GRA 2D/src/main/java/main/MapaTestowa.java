package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MapaTestowa implements MapInterface {
    //zmienna przechowujaca dane czy colidery zostały załadowane
    static boolean ColidersImported = false;
    static GamePanel gamePanel;
    //definiowanie potrzebny zmiennych grafik
    static BufferedImage drzewo,trawa,zemia,sciana,backgroundImage;

    //importowanie grafik i gamepanelu
    static {
        try {
            gamePanel = new GamePanel();
            backgroundImage = ImageIO.read(new File("src/main/resources/tlo.png"));
            trawa = ImageIO.read(new File("src/main/resources/trawa.png"));
            zemia = ImageIO.read(new File("src/main/resources/grassdirt.png"));
            drzewo = ImageIO.read(new File("src/main/resources/drzewo.png"));
            sciana = ImageIO.read(new File("src/main/resources/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //definiowanie lokalizacji obiektów na mapie
    static int[][] drzewoLocaltion = {{1500,690,1500+drzewo.getWidth(null),PlayerStats.groundStart+70}};
    static int[][] scianaLocation = {{400,690,400+sciana.getWidth(null),PlayerStats.groundStart+70}};

    //dodanie koliderów do poczekalni
    public void addColiders(){
        coliderController.addObjectColiderMap(scianaLocation);
        coliderController.addObjectColiderMap(drzewoLocaltion);
        coliderController.sortColiderMap();
    }

    //zaimportowanie coliderow oraz rysowanie ich co 1/60 sekundy
    public  void drawColider(){
        if(!ColidersImported){
            ColidersImported = !ColidersImported;
            addColiders();
        }
        coliderController.ColiderDrawing(coliderController.ColiderMap);
    }

    //funkcja odpowiedzialana za rysowanie obiektow na mapie
    public  void draw(Graphics2D g2) {
        GraphicDraw.DrawGroundObjects(zemia,g2,820);
        GraphicDraw.DrawGoundObjectsWithScale(trawa,g2,780,50,50);
        GraphicDraw.DrawObjects(drzewoLocaltion,drzewo,g2);
        GraphicDraw.DrawObjects(scianaLocation,sciana,g2);
    }
    //funckja odpowiedzialna za rysowanie backgroundu
    public  void drawBackground(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0,0, null);
    }
}
