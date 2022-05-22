package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class endlessMode implements MapInterface{

    BufferedImage drzewo = null;
    BufferedImage sciana = null;
    BufferedImage zemia = null;
    BufferedImage backgroundImage = null;

    BufferedImage ground;

    {
        try {
            ground = ImageIO.read(new File("src/main/resources/grassdirt.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[][] drzewoLocaltion = {{1500,690},{1700,690}};
    static int[][] scianaLocation = {};
    static int[][] kwiatLocation = {};
    static int[][] result={{1500,690},{1700,690}};
    static int lastChunkPosX= 0;
    static boolean execute = false;
    private void randomStyle() {
        if (!execute) {
            int x = (int) (Math.random() * 5);
            if (x >= 0) {
                try {
                    zemia = ImageIO.read(new File("src/main/resources/grassdirt.png"));
                    drzewo = ImageIO.read(new File("src/main/resources/drzewo.png"));
                    sciana = ImageIO.read(new File("src/main/resources/wall.png"));
                    backgroundImage = ImageIO.read(new File("src/main/resources/tlo.png"));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            execute = !execute;
        }
    }



    private  int[][] randomMap(){
        randomStyle();
        int[][] chunk1 = {{lastChunkPosX+100,690},{lastChunkPosX+250,690},{lastChunkPosX+320,690},{lastChunkPosX+500,690}};
        int x = (int) (Math.random()*3);
        lastChunkPosX = lastChunkPosX + chunk1[(chunk1.length-1)][1];
        result = Stream.concat(Arrays.stream(result),Arrays.stream(chunk1)).toArray(int[][]::new);
        return chunk1;
    }

    @Override
    public void addColiders() {
        int [][] colider = randomMap();
        coliderController.addObjectColiderMap(colider);
        coliderController.sortColiderMap();
        coliderController.bakeColiderMap(drzewo,colider);
    }

    @Override
    public void drawColider() {
        if(coliderController.ColiderMap.size() < 5){
            addColiders();
        }
        coliderController.ColiderDrawing(coliderController.ColiderMap);
    }

    @Override
    public void draw(Graphics2D g2) throws IOException {
        GraphicDraw.DrawObjects(result,drzewo,g2);
        GraphicDraw.DrawGroundObjects(ground,g2,820);
    }
    public void drawParticle(Graphics2D g2){
        Particles.DrawParticle(g2);
    }
    @Override
    public void drawBackground(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0,0, null);
    }
}
