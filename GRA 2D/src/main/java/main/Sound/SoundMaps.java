package main.Sound;

import main.Config;

import javax.sound.sampled.Clip;
import java.io.File;

public class SoundMaps extends AbstractSound {
    private Config config;
    //public static double volume = 0;

    public SoundMaps() {
        soundList.add("src/main/resources/sounds/maps/menu.wav");
        volume = config.SoundVolume;
    }
}
