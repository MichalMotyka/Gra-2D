package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MapaTestowa {
    public static ArrayList<Integer> arList = new ArrayList<>();
    static GamePanel gamePanel;
    static Image drzewo;
    static int[] drzewoLocaltion = {360, 560, 1500};
    static int[] scianaLocation = {400};
    static Image trawa;
    static Image zemia;
    static Image sciana;
    static Image backgroundImage;


    static boolean enter = true;
    static Player player = new Player(null, null);

    static {
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/tlo.png"));
            gamePanel = new GamePanel();
            arList.addAll(Arrays.asList(400, 680, 670, 800, 1500, 690, 1590, 830));
            trawa = ImageIO.read(new File("src/main/resources/trawa.png"));
            zemia = ImageIO.read(new File("src/main/resources/grassdirt.png"));
            drzewo = ImageIO.read(new File("src/main/resources/drzewo.png"));
            sciana = ImageIO.read(new File("src/main/resources/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void drawColider() {
        if (!enter) {
            PlayerStats.playersize = 0;
        } else {
            PlayerStats.playersize = 160;
        }
        if ((arList.get(0) + Player.worldMoveX >= Player.x +80 - Player.playerSpeed && MapaTestowa.arList.get(0) + Player.worldMoveX <= Player.x + 80 + Player.playerSpeed) && MapaTestowa.arList.get(1) <= Player.y) {

            Player.solid = true;
        }
        if ((MapaTestowa.arList.get(0) + Player.worldMoveX <= Player.x + 80 - Player.playerSpeed || MapaTestowa.arList.get(0) + Player.worldMoveX >= Player.x + 80 + Player.playerSpeed) && MapaTestowa.arList.get(1) < Player.y) {
            Player.solid = false;
        }
        if ((MapaTestowa.arList.get(0) + Player.worldMoveX >= Player.x-80 - Player.playerSpeed && MapaTestowa.arList.get(0) + Player.worldMoveX <= Player.x -80+ PlayerStats.playersize) && MapaTestowa.arList.get(1) > Player.y) {
            enter = !enter;
            Player.solid = false;
            Player.ground = MapaTestowa.arList.get(1) - 70;
            if (MapaTestowa.arList.size() >= 4) {
                MapaTestowa.arList.remove(0);
                MapaTestowa.arList.remove(0);
            }
        }

    }

    public static void draw(Graphics2D g2) {
        for (int x = 0; x < 1280 - player.getWorldMove(); x += 40) {
            g2.drawImage(zemia, x + player.worldMoveX, 820, null);
            g2.drawImage(trawa, x + player.worldMoveX, 800, 50, 50, null);
        }
        for (int x = 0; x <= drzewoLocaltion.length - 1; x++) {
            if (drzewoLocaltion[x] <= gamePanel.screenWidth - player.getWorldMove() && drzewoLocaltion[x] >= -200 - player.getWorldMove()) {
                g2.drawImage(drzewo, drzewoLocaltion[x] + player.getWorldMove(), 690, null);

            }

        }
        for (int x = 0; x <= scianaLocation.length - 1; x++) {
            if (scianaLocation[x] <= gamePanel.screenWidth - player.getWorldMove() && scianaLocation[x] >= -200 - player.getWorldMove()) {
                g2.drawImage(sciana, scianaLocation[x] + player.getWorldMove(), 690, null);

            }
        }
    }

    public static void drawBackground(Graphics2D g2) {
        g2.drawImage(backgroundImage, 0, 0, null);
    }
}
