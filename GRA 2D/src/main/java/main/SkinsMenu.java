package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SkinsMenu extends JPanel {
    JLabel logo = new JLabel("Happy Jump Square");
    JLabel description = new JLabel("Wybór skina");
    JButton left = new JButton();
    JButton right = new JButton();
    JButton confirm = new JButton("Wybierz");

    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    final int maxScreenRow = 20;
    final int maxScreenCol = 25;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    static BufferedImage skin;
    public Skins skins;
    private int licznik = 0;


    public SkinsMenu() throws IOException {
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(null);
        logo.setFont(new Font("Serif", Font.PLAIN, 42));
        description.setFont(new Font("Serif", Font.PLAIN, 38));

        left.setBounds(125, 400, 250, 150);
        right.setBounds(825, 400, 250, 150);
        confirm.setBounds(475, 800, 250, 60);
        logo.setBounds(475, 50, 250, 50);
        description.setBounds(475, 100, 250, 50);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);

        ImageIcon leftIcon = new ImageIcon("src/main/resources/left_arrow.png"); // load the image to a imageIcon
        Image leftIcon_img = leftIcon.getImage(); // transform it
        Image leftImg_icon = leftIcon_img.getScaledInstance(120, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        leftIcon = new ImageIcon(leftImg_icon);

        ImageIcon rightIcon = new ImageIcon("src/main/resources/right_arrow.png"); // load the image to a imageIcon
        Image rightIcon_img = rightIcon.getImage(); // transform it
        Image rightImg_icon = rightIcon_img.getScaledInstance(120, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        rightIcon = new ImageIcon(rightImg_icon);

        skins = new Skins();

        skin = ImageIO.read(new File("src/main/resources/skins", skins.skins.get(0)));


        left.setIcon(leftIcon);
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        left.setFocusPainted(false);

        right.setIcon(rightIcon);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);
        right.setFocusPainted(false);

        confirm.setBackground(new Color(255, 204, 179));

        this.add(logo);
        this.add(description);
        this.add(left, gbc);
        this.add(right, gbc);
        this.add(confirm, gbc);

        this.setFocusable(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        try {
            Image img = ImageIO.read(new File("src/main/resources/unknown.png"));
            g.drawImage(img, 0 ,0, null);
            g.drawImage(skin, 515, 400, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void addListenerToConfirmBtn(JFrame frame, SkinsMenu panelToRemove){
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu userMenu = null;
                userMenu = new UserMenu();
                userMenu.addListenerToPlayBtn(frame, userMenu);
                userMenu.addListenerToSkinsBtn(frame, userMenu);
                frame.add(userMenu);
                frame.repaint();
                frame.revalidate();
                frame.remove(panelToRemove);
            }
        });
    }
    void addListenerToLeftBtn(JFrame frame){
        this.left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(licznik > 0) licznik--;
                else licznik = skins.skins.size()-1;
                try {
                    skin = ImageIO.read(new File("src/main/resources/skins", skins.skins.get(licznik)));
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    void addListenerToRightBtn(JFrame frame){
        this.right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(licznik < skins.skins.size()-1) licznik++;
                else licznik = 0;
                try {
                    skin = ImageIO.read(new File("src/main/resources/skins", skins.skins.get(licznik)));
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


}
