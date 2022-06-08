package main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Skins {

    public ArrayList<String> skins = new ArrayList<>();

    public Skins() {
        File[] files = new File("src/main/resources/skins").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                skins.add(file.getName());
            }
        }
        //System.out.println(skins.get(0));
    }

}
