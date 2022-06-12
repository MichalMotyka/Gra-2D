package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChestsMenu extends JPanel {
    JLabel logo = new JLabel("Happy Jump Square");
    JLabel description = new JLabel("Losowanie skinów");
    JButton confirm = new JButton("Wyjdź");
    JButton draw = new JButton("Losuj");

    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    final int maxScreenRow = 20;
    final int maxScreenCol = 25;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    static BufferedImage chest;
    public Skins skins;
    private int licznik = 0;


    public ChestsMenu() throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(null);
        logo.setFont(new Font("Serif", Font.PLAIN, 42));
        description.setFont(new Font("Serif", Font.PLAIN, 38));

        confirm.setBounds(475, 800, 250, 60);
        draw.setBounds(475, 700, 250, 60);
        logo.setBounds(475, 50, 250, 50);
        description.setBounds(475, 100, 350, 50);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);

        chest = ImageIO.read(new File("src/main/resources/skrzynka1.png"));


        this.add(logo);
        this.add(description);
        this.add(confirm, gbc);
        this.add(draw, gbc);

        this.setFocusable(true);
        skins = new Skins();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        try {
            Image img = ImageIO.read(new File("src/main/resources/unknown.png"));
            g.drawImage(img, 0 ,0, null);
            g.drawImage(chest, 490, 400, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void addListenerToConfirmBtn(JFrame frame, ChestsMenu panelToRemove){
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu userMenu = null;
                userMenu = new UserMenu();
                userMenu.addListenerToPlayBtn(frame, userMenu);
                userMenu.addListenerToSkinsBtn(frame, userMenu);
                userMenu.addListenerToChestsBtn(frame, userMenu);
                frame.add(userMenu);
                frame.repaint();
                frame.revalidate();
                frame.remove(panelToRemove);
            }
        });
    }
    void addListenerToDrawBtn(JFrame frame) {
        this.draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String skin_rand =  skins.addSkins();
                    if(skin_rand != null) {
                        chest = ImageIO.read(new File("src/main/resources/skins", skin_rand));
                        frame.repaint();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


}
