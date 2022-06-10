package main;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler {

    public static void pressMouse(GamePanel gamePanel) {
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(x >= (gamePanel.screenWidth - 200) / 2 && x <= (gamePanel.screenWidth + 200)/ 2 && y >= 450 && y <= 450 + 100) {
                    System.out.println("działa");
                }
//                GamePanel.clicked = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public static MouseListener exitGamePanel(GamePanel gamePanel, JFrame frame, UserMenu userMenu) {
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(x >= (gamePanel.screenWidth - 200) / 2 && x <= (gamePanel.screenWidth + 200)/ 2 && y >= 450 && y <= 450 + 100) {
                    gamePanel.addListenerToMenuButton(frame, userMenu, gamePanel);
                    gamePanel.stopGameThread();
                }
//                GamePanel.clicked = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return null;
    }
}
