package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Menu extends JPanel {

    JLabel logo = new JLabel("Happy Jump Square");
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
//        this.setLayout(new GridBagLayout());
        this.setLayout(null);
        logo.setFont(new Font("Serif", Font.PLAIN, 42));

        loginBtn.setBounds(475, 150, 250, 60);
        registerBtn.setBounds(475, 400, 250, 60);
        logo.setBounds(475, 50, 250, 50);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        loginBtn.setBackground(new Color(255, 204, 179));
        registerBtn.setBackground(new Color(255, 204, 179));
//        loginBtn.setPreferredSize(new Dimension(250,60));
//        registerBtn.setPreferredSize(new Dimension(250,60));

//        gbc.fill = GridBagConstraints.NORTH;
//        gbc.gridx = 0;
//        gbc.gridy=0;;
        this.add(logo);
        this.add(loginBtn, gbc);
//        gbc.fill = GridBagConstraints.CENTER;
//        gbc.gridx = 0;
//        gbc.gridy=2;
        this.add(registerBtn, gbc);
//        this.setLayout(new GridLayout(2, 1));
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        try {
            Image img = ImageIO.read(new File("src/main/resources/unknown.png"));
            g.drawImage(img, 0 ,0, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
//                    Config.ActiveMap = "MapaTestowa";
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
