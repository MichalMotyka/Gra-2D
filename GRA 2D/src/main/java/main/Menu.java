package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JPanel {

    JButton loginBtn = new JButton("Zaloguj się");
    JButton registerBtn = new JButton("Zarejestruj się");
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    final int maxScreenRow = 20;
    final int maxScreenCol = 25;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    public Menu() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        loginBtn.setBackground(new Color(255, 204, 179));
        registerBtn.setBackground(new Color(255, 204, 179));
        loginBtn.setPreferredSize(new Dimension(150,40));
        registerBtn.setPreferredSize(new Dimension(150,40));

        gbc.fill = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy=0;
        this.add(loginBtn, gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy=2;
        this.add(registerBtn, gbc);
//        this.setLayout(new GridLayout(2, 1));
        this.setFocusable(true);
    }

    void addListenerToLoginBtn(JFrame frame, Menu panelToRemove) {
        this.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tekst = JOptionPane.showInputDialog("Nazwa użytkownika");
                Http http = new Http();
                String response;
                try {
                    response = http.loginUser(tekst);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if(response.contains("name")){
                    GamePanel gamePanel = null;
                    try {
                        gamePanel = new GamePanel();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Config.ActiveMap = "MapaTestowa";
                    frame.add(gamePanel);
                    frame.repaint();
                    frame.revalidate();
//                frame.setContentPane(panel);
                    gamePanel.startGameThread();
                    frame.remove(panelToRemove);
                } else {
                    JOptionPane.showMessageDialog(panelToRemove, "Podana nazwa uzytkwonika jest niepoprawna");
                }
            }
        });
    }

    void addListenerToRegisterBtn(){
        this.registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tekst = JOptionPane.showInputDialog("Podaj nazwę użytkownika do zarejestrowania:");
                Http http = new Http();
                String response;
                try {
                    response = http.registerUser(tekst);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(response);
            }
        });
    }
}
