package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapaPoziom1 implements MapInterface {
    //zmienna przechowujaca dane czy colidery zostały załadowane
    static boolean ColidersImported = false;
    static GamePanel gamePanel;
    //definiowanie potrzebny zmiennych grafik
    static BufferedImage drzewo,ziemia,sciana,backgroundGame,ziemia2,ziemia1Blok,ziemia2Blok,ziemia3Blok,ziemiaPodst2,ziemiaPodst3;

    //importowanie grafik i gamepanelu
    static {
        try {
            gamePanel = new GamePanel();
            backgroundGame = ImageIO.read(new File("src/main/resources/tlo-wiesv1.png")); //Element tła poziomu1 gry
            ziemia = ImageIO.read(new File("src/main/resources/trawaa.png")); //Element podłoża poziomu1 gry
            drzewo = ImageIO.read(new File("src/main/resources/drzewowies.png")); //Element dekoracji 1
            sciana = ImageIO.read(new File("src/main/resources/kamień2mnjeszy.png")); //Przeszkoda do pokonania przez gracza
            ziemia2 = ImageIO.read(new File("src/main/resources/ziemia2.png"));
            ziemia1Blok = ImageIO.read(new File ("src/main/resources/DirtBlockCol.png")); //Blok ziemi o podstawie 1x1(wysokość,szerokość)
            ziemia2Blok = ImageIO.read(new File ("src/main/resources/DirtBlockCol2.png")); //Blok ziemi  2x1
            ziemia3Blok = ImageIO.read(new File ("src/main/resources/DirtBlockCol3.png")); //Blok ziemi  3x1
            ziemiaPodst2 = ImageIO.read(new File ("src/main/resources/DirtBlockPodst2.png")); //Blok podstawy ziemi 1x2
            ziemiaPodst3 = ImageIO.read(new File ("src/main/resources/DirtBlockPodst3.png")); //Blok podstawy ziemi  1x3
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //definiowanie lokalizacji obiektów na mapie
    static int[][] drzewoLocation = {{700,620},{2200,620}};
    static int[][] scianaLocation = {{500,750},{1940,750},{4665,750},{4905,750},{4985,750}};
    static int[][] ziemia1BlokLocation = {{1600,739}, {1850,739},{3000,739},{4160,739},{4745,739},{4825,739},{5470,580},{5670,480},{10000,739}}; //80px
    static int[][] ziemia2BlokLocation = {{3080,655},{4080,655}};
    static int[][] ziemia3BlokLocation = {{1100,570}}; //{5700,520}
    static int[][] ziemiaPodst2Location = {{3160,655},{3540,655},{3920,655}}; //160px  {3163,655}
    static int[][] ziemiaPodst3Location = {{4665,460},{5130,660}}; //240px

    @Override
    //dodanie koliderów do poczekalni
    public void addColiders() {
        coliderController.addObjectColiderMap(scianaLocation);
        coliderController.addObjectColiderMap(ziemia1BlokLocation);
        coliderController.addObjectColiderMap(ziemia2BlokLocation);
        coliderController.addObjectColiderMap(ziemia3BlokLocation);
        coliderController.addObjectColiderMap(ziemiaPodst2Location);
        coliderController.addObjectColiderMap(ziemiaPodst3Location);
        coliderController.sortColiderMap();
        coliderController.bakeColiderMap(ziemia1Blok,ziemia1BlokLocation);
        coliderController.bakeColiderMap(ziemia2Blok,ziemia2BlokLocation);
        coliderController.bakeColiderMap(ziemia3Blok,ziemia3BlokLocation);
        coliderController.bakeColiderMap(ziemiaPodst2,ziemiaPodst2Location);
        coliderController.bakeColiderMap(ziemiaPodst3,ziemiaPodst3Location);
        coliderController.bakeColiderMap(sciana,scianaLocation);
    }

    @Override
    //zaimportowanie coliderow oraz rysowanie ich co 1/60 sekundy
    public void drawColider() {
        if(!ColidersImported){
            ColidersImported = !ColidersImported;
            addColiders();
        }
        coliderController.ColiderDrawing(coliderController.ColiderMap);
    }

    @Override
    //funkcja odpowiedzialana za rysowanie obiektow na mapie
    public void draw(Graphics2D g2) throws IOException {
       // GraphicDraw.DrawGoundObjectsWithScale(ziemia,g2,94,437,400);
        GraphicDraw.DrawGroundObjects(ziemia,g2,820);
        GraphicDraw.DrawObjects(drzewoLocation,drzewo,g2);
        GraphicDraw.DrawGroundObjects(ziemia2,g2,900);
        GraphicDraw.DrawObjects(scianaLocation,sciana,g2);
        GraphicDraw.DrawObjects(ziemia1BlokLocation,ziemia1Blok,g2);
        GraphicDraw.DrawObjects(ziemia2BlokLocation,ziemia2Blok,g2);
        GraphicDraw.DrawObjects(ziemia3BlokLocation,ziemia3Blok,g2);
        GraphicDraw.DrawObjects(ziemiaPodst2Location,ziemiaPodst2,g2);
        GraphicDraw.DrawObjects(ziemiaPodst3Location,ziemiaPodst3,g2);


    }
    public void drawParticle(Graphics2D g2){
        Particles.DrawParticle(g2);
    }
    //funckja odpowiedzialna za rysowanie backgroundu
    @Override
    public void drawBackground(Graphics2D g2) {GraphicDraw.drawAnimatedBackground(g2,backgroundGame);
    }
}

