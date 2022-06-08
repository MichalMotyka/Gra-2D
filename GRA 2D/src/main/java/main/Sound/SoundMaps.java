package main.Sound;

import main.Config;

import java.io.File;

public class SoundMaps extends AbstractSound {
    private Config config;

    public SoundMaps() {
        soundList.add("src/main/resources/sounds/maps/menu.wav");
        volume = volume = config.SoundVolume;
    }
}
