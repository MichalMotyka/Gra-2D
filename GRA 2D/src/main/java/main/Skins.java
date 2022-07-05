package main;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skins {

    public ArrayList<String> all_skins = new ArrayList<>();
    public ArrayList<String> own_skins = new ArrayList<>();
    public ArrayList<String> draw_skins = new ArrayList<>();
    private File file;

    public Skins() {

        try {
            file = new File("src/main/resources/files/owned_skins.txt");
            this.getOwnedSkins();
            this.getAllSkins();
            this.getDrawSkins();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(skins.get(0));
    }

    private void getAllSkins() {
        File[] files = new File("src/main/resources/skins").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                all_skins.add(file.getName());
            }
        }
    }
    private void getOwnedSkins() throws IOException {
        //File file = new File("src/main/resources/owned_skins.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            own_skins.add(st);
            //System.out.println(st);
        }
    }
    private void getDrawSkins() {
        draw_skins.addAll(all_skins);
        draw_skins.removeAll(own_skins);
    }
    public void update() throws IOException {
        all_skins.clear();
        own_skins.clear();
        draw_skins.clear();
        this.getOwnedSkins();
        this.getAllSkins();
        this.getDrawSkins();
    }
    private String getRandom() {
        int size = draw_skins.size();
        double rand = Math.random() * size;
        int random =(int)rand;
        return draw_skins.get(random);
    }

    public String addSkins() throws IOException {
       // File file = new File("src/main/resources/owned_skins.txt");
        if(draw_skins.size() > 0) {
            String rand = getRandom();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(rand);
            pw.close();
            System.out.println(getRandom());
            this.update();
            return rand;
        }
        return null;
    }
}
