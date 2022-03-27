package main;

import javax.swing.*;
import java.io.IOException;
//Głowna metoda tworząca ramke razem z oknem
public class main {
    public static void main(String args[]) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GRA");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
