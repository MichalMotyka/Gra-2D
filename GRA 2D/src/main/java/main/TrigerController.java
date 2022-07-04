package main;

import lombok.SneakyThrows;

import java.awt.*;
import java.util.ArrayList;

public class TrigerController {
    static ArrayList<Triger> TrigerList = new ArrayList<Triger>();
    static KeyHandler keyHandler = new KeyHandler();
    static boolean death = false;
    public static void addTriger(){
        Triger triger = new Triger(820,730,10,10, Triger.Type.DEATH);
        TrigerList.add(triger);
        Triger triger1 = new Triger(690,730,10,10, Triger.Type.COIN);
        TrigerList.add(triger1);
    }

    static void UpdateTriger(){
        if(!keyHandler.stopPressed) {
            for (Triger triger : TrigerList) {
                triger.setX(triger.getX() - Player.playerSpeed);
            }
        }
    }
    static void DrawTriger(Graphics2D g2){
        for (Triger triger:TrigerList) {
            g2.setColor(Color.YELLOW);
            g2.fillRect(triger.getX(),triger.getY(),triger.getWidth(),triger.getHeight());
        }
    }
    @SneakyThrows
    public static boolean validColision(Graphics2D g2){
        UpdateTriger();
        DrawTriger(g2);
        for (Triger triger:TrigerList) {
            if ( triger.getTrigerType() == Triger.Type.DEATH&& (triger.getX() >= Player.x + 80 - Player.playerSpeed && triger.getX()  <= Player.x + 80 + Player.playerSpeed) &&
                    (triger.getY() <= Player.y+50 && triger.getY() >= Player.y)) {
                death = true;
//                while (true){
//                 Thread.sleep(1000);
//                }
            }
            if ( triger.getTrigerType() == Triger.Type.COIN&& (triger.getX() >= Player.x + 80 - Player.playerSpeed && triger.getX()  <= Player.x + 80 + Player.playerSpeed) &&
                    (triger.getY() <= Player.y+50 && triger.getY() >= Player.y)) {
                System.out.println("Dostałeś monetę");
                TrigerList.remove(triger);
                return true;
            }
        }
        return false;
    }
}
