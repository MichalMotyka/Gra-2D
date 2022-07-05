package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MapaTestowa implements MapInterface {
    //zmienna przechowujaca dane czy colidery zostały załadowane
    static boolean ColidersImported = false;
    static GamePanel gamePanel;
    //definiowanie potrzebny zmiennych grafik
    static BufferedImage drzewo,trawa,zemia,sciana,kwiat,kwiat2,backgroundImage;

    //importowanie grafik i gamepanelu
    static {
        try {
            gamePanel = new GamePanel();
            backgroundImage = ImageIO.read(new File("src/main/resources/tlo.png"));
            trawa = ImageIO.read(new File("src/main/resources/trawa.png"));
            zemia = ImageIO.read(new File("src/main/resources/grassdirt.png"));
            drzewo = ImageIO.read(new File("src/main/resources/drzewo.png"));
            sciana = ImageIO.read(new File("src/main/resources/wall.png"));
            kwiat = ImageIO.read(new File("src/main/resources/kwiat.png"));
            kwiat2 =ImageIO.read(new File("src/main/resources/kawiatRED.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //definiowanie lokalizacji obiektów na mapie
    static int[][] drzewoLocaltion = {{930,650}, {1600,650}};
    static int[][] scianaLocation = {{400,690}};
    static int[][] kwiatLocation = {{1200,800},{1205,800},{1210,800},{1215,800},{1220,800},{1225,800},{1230,800},{1235,800},{1240,800},{1245,800},{1250,800}};

    //dodanie koliderów do poczekalni
    public void addColiders(){
        TrigerController.addTriger();
        coliderController.addObjectColiderMap(scianaLocation);
        coliderController.addObjectColiderMap(drzewoLocaltion);
        coliderController.sortColiderMap();
        coliderController.bakeColiderMap(drzewo,drzewoLocaltion);
        coliderController.bakeColiderMap(sciana,scianaLocation);
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
    public  void draw(Graphics2D g2) throws IOException {
        Particles.drawRainParticle(g2);
        GraphicDraw.DrawGroundObjects(zemia,g2,820);
        GraphicDraw.DrawGoundObjectsWithScale(trawa,g2,780,50,50);
        GraphicDraw.DrawObjects(drzewoLocaltion,drzewo,g2);
        GraphicDraw.DrawObjects(scianaLocation,sciana,g2);
        GraphicDraw.DrawAnimatedObject(kwiatLocation,kwiat,1200,750,g2);
        TrigerController.validColision(g2);
    }
    public void drawParticle(Graphics2D g2){
        Particles.DrawParticle(g2);
    }
    //funckja odpowiedzialna za rysowanie backgroundu
    public  void drawBackground(Graphics2D g2) {
        //g2.drawImage(backgroundImage, 0,0, null);
    }
}
