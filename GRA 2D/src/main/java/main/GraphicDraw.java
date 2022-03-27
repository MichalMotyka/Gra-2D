package main;

import java.awt.*;
import java.io.IOException;

public class GraphicDraw {
    static GamePanel gamePanel;
    static Player player = new Player(null, null);

    static {
        try {
            gamePanel = new GamePanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //funckja odpowiedzialna za rysowanie obiektów np drzewo
    public static void DrawObjects(int[][] object, Image objectImage, Graphics2D g2) {
        for (int x = 0; x <= object.length - 1; x++) {
            if (object[x][0] <= gamePanel.screenWidth+500 - player.getWorldMove() && object[x][0] >= -200 - player.getWorldMove()) {
                g2.drawImage(objectImage, object[x][0] + player.getWorldMove(), object[x][1], null);
            }
        }
    }
    //funckja odpowiedzialna za rysowanie obiektow powtarzalnych np trawa
    public static void DrawGroundObjects(Image objectImage,Graphics2D g2,int y){
        for (int x = 0; x < gamePanel.screenWidth - player.getWorldMove(); x += 40) {
            g2.drawImage(objectImage, x + player.worldMoveX, y, null);
        }
    }
    //funckja odpowiedzialna za rysowanie obiektow powtarzalnych np trawa wraz z skalla
    public static void DrawGoundObjectsWithScale(Image objectImage,Graphics2D g2,int y,int width,int height){
        for (int x = 0; x < gamePanel.screenWidth - player.getWorldMove(); x += 40) {
            g2.drawImage(objectImage, x + player.worldMoveX, y, width, height, null);
        }
    }
}
