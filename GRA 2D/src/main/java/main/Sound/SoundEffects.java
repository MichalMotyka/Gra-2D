package main.Sound;

import main.Config;

import javax.sound.sampled.Clip;

public class SoundEffects extends AbstractSound {
    private Config config;
    //public static double volume = 0;

    public SoundEffects(){
        soundList.add("src/main/resources/sounds/effects/jump.wav");
        volume = config.EffectVolume;
    }
}
