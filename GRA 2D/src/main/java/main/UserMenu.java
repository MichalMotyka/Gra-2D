package main;

import lombok.SneakyThrows;
import main.Sound.SoundMaps;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UserMenu extends JPanel {
    JLabel logo = new JLabel("Happy Jump Square");
    JButton playBtn = new JButton("Graj");
    JButton skinsBtn = new JButton("Wybór skina");
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    final int maxScreenRow = 20;
    final int maxScreenCol = 25;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    public UserMenu() {
        GridBagConstraints gbc = new GridBagConstraints();
//        this.setLayout(new GridBagLayout());
        this.setLayout(null);
        logo.setFont(new Font("Serif", Font.PLAIN, 42));

        playBtn.setBounds(475, 150, 250, 60);
        skinsBtn.setBounds(475, 250, 250, 60);
        logo.setBounds(475, 50, 250, 50);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);

        playBtn.setBackground(new Color(255, 204, 179));
        skinsBtn.setBackground(new Color(255, 204, 179));

        this.add(logo);
        this.add(playBtn, gbc);
        this.add(skinsBtn, gbc);

        this.setFocusable(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        try {
            Image img = ImageIO.read(new File("src/main/resources/unknown.png"));
            g.drawImage(img, 0 ,0, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void addListenerToPlayBtn(JFrame frame, UserMenu panelToRemove) {
        this.playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel gamePanel = null;
                try {
                    gamePanel = new GamePanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.add(gamePanel);
                frame.repaint();
                frame.revalidate();
                gamePanel.startGameThread();
                frame.remove(panelToRemove);
            }
        });
    }

    void addListenerToSkinsBtn(JFrame frame, UserMenu panelBefore){
        this.skinsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkinsMenu skinsMenu = null;
                try {
                    skinsMenu = new SkinsMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                skinsMenu.addListenerToConfirmBtn(frame, skinsMenu);
                skinsMenu.addListenerToLeftBtn(frame);
                skinsMenu.addListenerToRightBtn(frame);
                frame.add(skinsMenu);
                frame.repaint();
                frame.revalidate();
                frame.remove(panelBefore);
            }
        });
    }
}
