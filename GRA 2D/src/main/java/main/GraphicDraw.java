package main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicDraw {
    static GamePanel gamePanel;
    static Player player = new Player(null, null);
    static int fps = 0;
    static int state = 0;
    static int lastpos = 0;
    static ArrayList<BackgroundAnimated> images = new ArrayList<>();

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
            if (object[x][0] <= gamePanel.screenWidth + 500 - player.getWorldMove() && object[x][0] >= -200 - player.getWorldMove()) {
                g2.drawImage(objectImage, object[x][0] + player.getWorldMove(), object[x][1], null);
            }
        }
    }

    //funckja odpowiedzialna za rysowanie obiektow powtarzalnych np trawa
    public static void DrawGroundObjects(Image objectImage, Graphics2D g2, int y) {
        for (int x = 0; x < gamePanel.screenWidth - player.getWorldMove(); x += 40) {
            g2.drawImage(objectImage, x + player.worldMoveX, y, null);
        }
    }

    //funckja odpowiedzialna za rysowanie obiektow powtarzalnych np trawa wraz z skalla
    public static void DrawGoundObjectsWithScale(Image objectImage, Graphics2D g2, int y, int width, int height) {
        for (int x = 0; x < gamePanel.screenWidth - player.getWorldMove(); x += 40) {
            g2.drawImage(objectImage, x + player.worldMoveX, y, width, height, null);
        }
    }


    public static void DrawAnimatedObject(int[][] object, BufferedImage image, int x, int y, Graphics2D g2) {
        if (x <= gamePanel.screenWidth + 500 - player.getWorldMove()) {
            fps++;
            double radius = 0;
            if (fps <= 60 && state == 0) {
                radius = fps * 0.15;
                if (fps == 60) {
                    state = 1;
                }
            }
            if (fps <= 60 && state == 2) {
                radius = (-1 * fps) * 0.15;
                if (fps == 60) {
                    state = 3;
                }
            }
            if (fps > 0 && (state == 1 || state == 3)) {
                fps -= 2;
                if (fps <= 1) {
                    if (state == 1)
                        state++;
                }
                if (state == 3) {
                    state = 0;
                    fps = fps * -1;
                }
                radius = fps * 0.20;
            }
            double rotationRequired = Math.toRadians(radius);
            double locationX = image.getWidth() / 2;
            double locationY = image.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g2.drawImage(op.filter(image, null), x + player.getWorldMove(), y, null);
        }
    }

    public static void drawAnimatedBackground(Graphics2D g2, Image image) {
        for (int x = 0; x < 7; x++) {
            if (images.size() <= 7) {
                if (images.size() < 1) {
                    images.add(new BackgroundAnimated(image, 0, 0));
                } else {
                    images.add(new BackgroundAnimated(image, images.get(images.size() - 1).getX() + image.getWidth(null), 0));
                }
            }
        }
        for (BackgroundAnimated backgroundAnimated : images) {
            if (lastpos < backgroundAnimated.getX()) {
                lastpos = backgroundAnimated.getX();
            }
            if (backgroundAnimated.getGrafika().getWidth(null) + backgroundAnimated.getX() >= 0) {
                backgroundAnimated.setX(backgroundAnimated.getX() - 1);
                g2.drawImage(backgroundAnimated.getGrafika(), backgroundAnimated.getX(), backgroundAnimated.getY(), null);
            } else {
                backgroundAnimated.setX(lastpos - 1);
                g2.drawImage(backgroundAnimated.getGrafika(), backgroundAnimated.getX(), backgroundAnimated.getY(), null);
            }
        }
    }
}
