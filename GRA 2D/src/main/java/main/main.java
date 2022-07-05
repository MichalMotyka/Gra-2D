package main;

import main.Sound.SoundEffects;
import main.Sound.SoundMaps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//Głowna metoda tworząca ramke razem z oknem
public class main {
    private static SoundMaps mSound;
    private static SoundEffects eSound;
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GRA");
        Menu menuPanel = new Menu();
        menuPanel.addListenerToLoginBtn(window, menuPanel);
        menuPanel.addListenerToRegisterBtn();
        menuPanel.addListenerToSettingsBtn(window, menuPanel);
        window.add(menuPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        mSound = new SoundMaps();
        eSound = new SoundEffects();
        mSound.setFile(0);
        mSound.loop();
    }
}
