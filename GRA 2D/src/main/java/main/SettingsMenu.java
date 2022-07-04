package main;

import main.Sound.SoundEffects;
import main.Sound.SoundMaps;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsMenu extends JPanel {
    private Config config;
    JLabel logo = new JLabel("Happy Jump Square");
    JLabel description = new JLabel("Ustawienia");
    JLabel volume_m_desc = new JLabel("Głośność muzyki:");
    JLabel volume_m_val = new JLabel("50%");
    JLabel volume_e_desc = new JLabel("Głośność efektów:");
    JLabel volume_e_val = new JLabel("50%");
    JButton confirm = new JButton("Wyjdź");
    JSlider slider_volm;
    JSlider slider_vole;

    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    final int maxScreenRow = 20;
    final int maxScreenCol = 25;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    private int licznik = 0;
    public SoundMaps mSound;
    public SoundEffects eSound;
    private int music_volume = 0;
    private int effect_volume = 0;


    public SettingsMenu() throws IOException {
        mSound = new SoundMaps();
        eSound = new SoundEffects();
        music_volume = (int) (mSound.volume*1000);
        effect_volume = (int) (eSound.volume*1000);
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(null);
        logo.setFont(new Font("Serif", Font.PLAIN, 42));
        description.setFont(new Font("Serif", Font.PLAIN, 38));
        volume_m_desc.setFont(new Font("Serif", Font.PLAIN, 38));
        volume_m_val.setFont(new Font("Serif", Font.PLAIN, 38));
        volume_e_desc.setFont(new Font("Serif", Font.PLAIN, 38));
        volume_e_val.setFont(new Font("Serif", Font.PLAIN, 38));
        slider_volm = new JSlider(0, 100, music_volume);
        slider_vole = new JSlider(0, 100, effect_volume);


        confirm.setBounds(475, 800, 250, 60);
        logo.setBounds(475, 50, 250, 50);
        description.setBounds(475, 100, 350, 50);
        volume_m_desc.setBounds(100, 300, 350, 50);
        volume_m_val.setBounds(860, 300, 350, 50);
        volume_e_desc.setBounds(100, 400, 350, 50);
        volume_e_val.setBounds(860, 400, 350, 50);
        slider_volm.setBounds(440, 305, 350, 50);
        slider_vole.setBounds(440, 405, 350, 50);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);// slider.setPreferredSize(new Dimension(400, 200));

        slider_volm.setOpaque(false);
        slider_vole.setOpaque(false);
        volume_m_val.setText(String.valueOf(music_volume));
        volume_e_val.setText(String.valueOf(effect_volume));
        slider_volm.addChangeListener(new ChangeListener() {


            @Override
            public void stateChanged(ChangeEvent e) {
                config.SoundVolume = slider_volm.getValue()*0.01;
                mSound.setVol(slider_volm.getValue()*0.01, mSound.clip);
                music_volume = slider_volm.getValue();
                volume_m_val.setText(String.valueOf(music_volume));
                //System.out.println(slider.getValue()*0.01);
            }
        });
        slider_vole.addChangeListener(new ChangeListener() {


            @Override
            public void stateChanged(ChangeEvent e) {
                config.EffectVolume = slider_vole.getValue()*0.01;
                effect_volume = slider_vole.getValue();
                volume_e_val.setText(String.valueOf(effect_volume));
                //System.out.println(slider.getValue()*0.01);
            }
        });

        this.add(slider_volm);
        this.add(slider_vole);
        this.add(logo);
        this.add(description);
        this.add(volume_m_desc);
        this.add(volume_m_val);
        this.add(volume_e_desc);
        this.add(volume_e_val);
        this.add(confirm, gbc);
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
    void addListenerToConfirmBtn(JFrame frame, SettingsMenu panelToRemove){
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = null;
                menu = new Menu();
                menu.addListenerToLoginBtn(frame, menu);
                menu.addListenerToRegisterBtn();
                menu.addListenerToSettingsBtn(frame, menu);
                frame.add(menu);
                frame.repaint();
                frame.revalidate();
                frame.remove(panelToRemove);
            }
        });
    }
}
