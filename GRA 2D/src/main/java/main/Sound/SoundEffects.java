package main.Sound;

import main.Config;

public class SoundEffects extends AbstractSound {
    private Config config;

    public SoundEffects(){
        soundList.add("src/main/resources/sounds/effects/jump.wav");
        volume = config.EffectVolume;
    }
}
