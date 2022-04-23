package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//Głowna metoda tworząca ramke razem z oknem
public class main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GRA");
        Menu menuPanel = new Menu();
        menuPanel.addListenerToLoginBtn(window, menuPanel);
        menuPanel.addListenerToRegisterBtn();
        window.add(menuPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
